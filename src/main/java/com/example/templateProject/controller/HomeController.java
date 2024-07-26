package com.example.templateProject.controller;

import com.example.templateProject.client.PersonsClient;
import com.example.templateProject.entity.User;
import com.example.templateProject.service.CoreService;
import com.example.templateProject.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @Autowired
    CoreService coreService;

    @Autowired
    EmailService emailService;



    @Autowired
    PersonsClient personsClient;

    @GetMapping("/getPersons")
    public ResponseEntity<Object> getPersons(){
        log.info("Recieved a request to get Persons from an api");
        return personsClient.getRandomPersons();
    }

//    Manual trigger to send a mail
    @GetMapping("/sendMail")
    public ResponseEntity<Object> sendMail(){
        log.info("Recieved a request to send a mail");
        emailService.sendMail();
        return ResponseEntity.ok("Sent mail successfully");
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id){
        log.info("Recieved a request to get  user by id from mongodb");
        return coreService.getUserById(id);
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        log.info(user.toString());
        log.info("Recieved a request to add a user to mongodb");
        return ResponseEntity.ok(coreService.addUser(user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> addUser( @PathVariable String id){
        log.info("Recieved a request to add a user to mongodb");
        coreService.removeUser(id);
        return ResponseEntity.ok("User is removed successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        log.info("Recieved a request to get all users from mongodb");
        return ResponseEntity.ok(coreService.getAllUsers());
    }


    @GetMapping("/add")
    public ResponseEntity<Integer> add(@RequestParam  int x , @RequestParam int y){
        log.info("Recieved a request to add 2 numbers");
        return ResponseEntity.ok(coreService.add(x,y));
    }

    @GetMapping("/sub")
    public ResponseEntity<Integer> sub(@RequestParam  int x , @RequestParam int y){
        log.info("Recieved a request to sub 2 numbers");
        return ResponseEntity.ok(coreService.sub(x,y));
    }

    @GetMapping("/mul")
    public ResponseEntity<Integer> mul(@RequestParam  int x , @RequestParam int y){
        log.info("Recieved a request to mul 2 numbers");
        return ResponseEntity.ok(coreService.mul(x,y));
    }

    @GetMapping("/div")
    public ResponseEntity<Integer> div(@RequestParam  int x , @RequestParam int y){
        log.info("Recieved a request to div 2 numbers");
        return ResponseEntity.ok(coreService.div(x,y));
    }

}
