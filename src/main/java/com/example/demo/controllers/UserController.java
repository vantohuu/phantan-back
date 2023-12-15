package com.example.demo.controllers;

import com.example.demo.models.ResponseObject;
import com.example.demo.models.User;
import com.example.demo.repositories.UserReponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "user/")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserReponsitory repositories;

    @RequestMapping("")
        //this request is: http://localhost:8080/api/v1/products
    List<User> getAllProducts() {
        return repositories.findAll();//where is data
    }

    @RequestMapping("/{username}")
        //this request is: http://localhost:8080/api/v1/products/{id}
        //let's return an object wih: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable String username) {
        Optional<User> foundProduct = repositories.findById(String.valueOf(username));
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query user successfully", foundProduct)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Not found Id", foundProduct));
    }

    @PostMapping(value = "/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        Optional<User> foundUsers = repositories.findById(newUser.getUsername().trim());
        if (foundUsers.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "user is exists", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "insert user successfully", repositories.save(newUser))
        );
    }

    @PostMapping(value = "/login")
    ResponseEntity<ResponseObject> login(@RequestBody User newUser) {
        System.out.println(newUser);
        Optional<User> foundUser = repositories.findById(newUser.getUsername().trim());
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(newUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "user is exists", foundUser)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "wrong password or username", null)
        );
    }

    @PutMapping("/{username}")
    ResponseEntity<ResponseObject> putProduct(@RequestBody User newUser, @PathVariable String username) {
        User updateUser = repositories.findById(username).map(
                user -> {
                    user.setUsername(newUser.getUsername());
                    user.setHo(newUser.getHo());
                    user.setTen(newUser.getTen());
                    user.setDiachi(newUser.getDiachi());
                    return repositories.save(user);
                }
        ).orElseGet(() -> {
            newUser.setDiachi(username);
            return repositories.save(newUser);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "update user successfully", updateUser)
        );
    }
}