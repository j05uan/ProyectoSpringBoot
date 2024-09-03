package com.survey.survey.roles.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;
import com.survey.survey.roles.application.service.IRoleservice;
import com.survey.survey.roles.domain.entity.Roles;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private IRoleservice iRoleservice;

    @GetMapping("/{id}")
    public ResponseEntity<Roles> show(@PathVariable Long id){
        Optional<Roles> rolesOp = iRoleservice.findByID(id);
        return rolesOp.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Roles> list(){
        return iRoleservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Roles> create(@RequestBody Roles roles){
        iRoleservice.save(roles);
        return new ResponseEntity<>(roles, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles>update(@PathVariable Long id, @RequestBody Roles roles){
        Optional<Roles> existingRoles = iRoleservice.findByID(id);

        if( existingRoles.isPresent()){
            roles.setId(id);

            Roles updateResponse = iRoleservice.save(roles);
            return  new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iRoleservice.findByID(id).isPresent()){
            iRoleservice.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

