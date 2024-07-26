package com.example.templateProject.controller;

import com.example.templateProject.client.PersonsClient;
import com.example.templateProject.entity.User;
import com.example.templateProject.service.CoreService;
import com.example.templateProject.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {


    @Mock
    private CoreService coreService;

    @Mock
    private EmailService emailService;

    @Mock
    private PersonsClient personsClient;

    @InjectMocks
    private HomeController homeController;


    @Test
    void getUserById_userFound() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("1","sai","sai@gmail.com");
        when(coreService.getUserById("1")).thenReturn(ResponseEntity.status(HttpStatus.OK).body(user));

        ResponseEntity<Object> response = homeController.getUserById("1");

        assertEquals(HttpStatus.OK , response.getStatusCode());
        assertEquals(objectMapper.writeValueAsString(user) , objectMapper.writeValueAsString(response.getBody()));
    }


    @Test
    void getUserById_userNotFound() throws Exception {
        when(coreService.getUserById("1")).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));

        ResponseEntity<Object> response = homeController.getUserById("1");

        assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}