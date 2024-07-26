package com.example.templateProject.config;

import com.example.templateProject.model.PersonsResponse;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

import java.time.Duration;

@Configuration
@Slf4j
public class WebClientConfig {


    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().secure(spec -> spec.sslContext(SslContextBuilder.forClient())
                                .defaultConfiguration(SslProvider.DefaultConfigurationType.TCP)
                                .handshakeTimeout(Duration.ofSeconds(200000))
                                .closeNotifyFlushTimeout(Duration.ofSeconds(10))
                                .closeNotifyReadTimeout(Duration.ofSeconds(10)))
                                .resolver(DefaultAddressResolverGroup.INSTANCE).secure()))
                .codecs((codecs -> codecs.defaultCodecs().maxInMemorySize(500000)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .filter(logResponse())
                .filter(handleError())
                .build();
    }

    private ExchangeFilterFunction logRequest(){
        return  ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request will be printed here");
            log.info(clientRequest.toString());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse(){
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response will be printed here");
            log.info("Response body  {}" , clientResponse.toString());
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction handleError(){
        return ExchangeFilterFunction.ofResponseProcessor( response -> {
            if(response.statusCode() != null && !response.statusCode().is2xxSuccessful()){
                log.info("Error handling takes place here");
                return  response.bodyToMono(String.class).flatMap(body -> {
                    return Mono.error(new Exception("Some error orccured"));
                });
            }else{
                return Mono.just(response);
            }
        });
    }


}
