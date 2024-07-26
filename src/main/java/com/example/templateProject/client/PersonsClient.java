package com.example.templateProject.client;

import com.example.templateProject.model.PersonsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@Slf4j
public class PersonsClient {

    @Autowired
    private WebClient webClient;

    public ResponseEntity<Object> getRandomPersons(){
        log.info("Api call will happen to fetch some random Persons");
        return  ResponseEntity.ok(webClient.get().uri("https://fakerapi.it/api/v1/persons")
                .header("MY_RANDOM_HEADER","SOME RANDOM VALUE")
                .retrieve()
                .bodyToMono(PersonsResponse.class)
                .retryWhen(Retry.fixedDelay(2 , Duration.ofSeconds(2)))
                .block());

    }


}
