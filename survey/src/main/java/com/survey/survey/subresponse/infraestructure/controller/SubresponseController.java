package com.survey.survey.subresponse.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;
import com.survey.survey.subresponse.application.service.ISubresponseservice;
import com.survey.survey.subresponse.domain.entity.Subresponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/subresponse")
public class SubresponseController {

    @Autowired
    private ISubresponseservice iSubresponseservice;

    @GetMapping("/{id}")
    public ResponseEntity<Subresponse> show(@PathVariable Long id) {
        Optional<Subresponse> subresponseOptional = iSubresponseservice.findByID(id);
        
        return subresponseOptional.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Subresponse> list(){
        return iSubresponseservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Subresponse> create(@RequestBody Subresponse entity) {    
        iSubresponseservice.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subresponse> update (@PathVariable Long id, @RequestBody Subresponse entity) {
        Optional<Subresponse> existingSubresponse = iSubresponseservice.findByID(id);

        if( existingSubresponse.isPresent()){
            entity.setId(id);

            Subresponse updateResponse = iSubresponseservice.save(entity);
            return  new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iSubresponseservice.findByID(id).isPresent()){
            iSubresponseservice.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    

}
