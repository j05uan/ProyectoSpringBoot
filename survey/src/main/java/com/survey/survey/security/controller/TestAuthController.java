package com.survey.survey.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class TestAuthController {

    @GetMapping("/get")
    @PreAuthorize("READ")
    public String helloget(){
        return "hello word - get";
    }

    @PostMapping("/post")
    public String hellopost(){
        return "hello word - post";
    }

    @PutMapping("/put")
    public String helloput(){
        return "hello word - put";
    }

    @DeleteMapping("/delete")
    public String helloDelete(){
        return "hello word - Delete";
    }

    @PatchMapping("/patch")
    public String hellopatch(){
        return "hello word - patch";
    }

    @PatchMapping("/login")
    public String login(){
        return "user";
    }

    

}
