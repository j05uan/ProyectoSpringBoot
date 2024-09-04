package com.survey.survey.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.security.controller.dto.AuthCreateUser;
import com.survey.survey.security.controller.dto.AuthLoginRequest;
import com.survey.survey.security.controller.dto.AuthResponse;
import com.survey.survey.security.service.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class Authenticationcontroller {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(AuthCreateUser authCreateUser){
        return  new ResponseEntity<>(this.userDetailServiceImpl.createUser(authCreateUser), HttpStatus.CREATED);

    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailServiceImpl.loginUser(userRequest), HttpStatus.OK);
    }

}
