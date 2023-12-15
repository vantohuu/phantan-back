package com.example.demo.controllers;

import com.example.demo.models.CTVe;
import com.example.demo.models.User;
import com.example.demo.models.Ve;
import com.example.demo.repositories.CTVeReponsitory;
import com.example.demo.repositories.VeReponsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repositories.UserReponsitory;

import java.util.Collection;


@RestController
public class Test {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private VeReponsitory veRepo;
    @Autowired
    private UserReponsitory userRepo;

    @Autowired
    private CTVeReponsitory ctveRepo;

    @RequestMapping("/")
    public String Hello(){
        return "Chao Lan";
    }
    @RequestMapping("/ve")
    public Iterable<Ve> getAllVe(){

        return veRepo.findAll();
    }

    @RequestMapping("/user")
    public Iterable<User> getAllUser(){
        return userRepo.findAll();
    }

    @RequestMapping("/ctve")
    public Iterable<CTVe> getAllCTVe(){
        Collection<CTVe> l = ctveRepo.findAll();
        return ctveRepo.findAll();
    }
}