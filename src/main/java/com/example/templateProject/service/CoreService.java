package com.example.templateProject.service;

import com.example.templateProject.entity.User;
import com.example.templateProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CoreService {

    @Autowired
    UserRepository userRepo;


    public ResponseEntity<Object> getUserById(String id){
        log.info("getting user from mongodb");
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.isPresent()? ResponseEntity.status(HttpStatus.OK).body(userOptional.get())  : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    public String addUser(User user){
        log.info("adding a user to the mongodb");
        Optional<User> userOptional = userRepo.findById(user.getId());
        if(userOptional.isPresent()){
            return "User with this id already exists";
        }
        userRepo.save(user);
        return "User added successfully";
    }

    public void removeUser(String id){
        log.info("Removing a user in the mongodb");
        userRepo.deleteById(id);
    }


    public List<User> getAllUsers(){
        log.info("Getting all users from mongodb");
        return userRepo.findAll();
    }


    public Integer add(int x , int y){
        log.info("returning sum of 2 numbers");
        return x+y;
    }

    public Integer sub(int x , int y){
        log.info("returning sub of 2 numbers");
        return x-y;
    }


    public Integer mul(int x , int y){
        log.info("returning mul of 2 numbers");
        return x*y;
    }


    public Integer div(int x , int y){
        log.info("returning div of 2 numbers");
        return x/y;
    }
}
