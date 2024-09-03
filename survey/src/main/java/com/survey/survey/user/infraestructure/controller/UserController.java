package com.survey.survey.user.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.user.application.service.IUserservice;
import com.survey.survey.user.domain.entity.Users;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserservice iUserservice;

    @GetMapping("/{id}")
    public ResponseEntity<Users> show(@PathVariable Long id) {
        Optional<Users> users = iUserservice.findByID(id);

        return users.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    
    }

    @GetMapping
    public List<Users>list(){
        return iUserservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Users> create(@RequestBody Users entity) {
   
        iUserservice.save(entity);
        
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users entity) {
        Optional<Users> existingUSers = iUserservice.findByID(id);

         if (existingUSers.isPresent()) {
            entity.setId(id);

        Users updatedUsers = iUserservice.save(entity);

        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iUserservice.findByID(id).isPresent()){
            iUserservice.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
