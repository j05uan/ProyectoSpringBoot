package com.survey.survey.responseQuestion.infraestructure.controller;

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


import com.survey.survey.responseQuestion.application.service.IResponseQuestionservice;
import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;

@RestController
@RequestMapping("/api/response_questions")
public class ResponseQuestionController {

    @Autowired
    private  IResponseQuestionservice iResponseQuestionservice;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseQuestion> show(@PathVariable Long id){
        Optional<ResponseQuestion> resposeOp = iResponseQuestionservice.findByID(id);
        return resposeOp.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<ResponseQuestion> list(){
        return iResponseQuestionservice.getAll();
    }

    @PostMapping()
    public ResponseEntity<ResponseQuestion> create (@RequestBody ResponseQuestion entity) {
        iResponseQuestionservice.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseQuestion> update(@PathVariable Long id, @RequestBody ResponseQuestion responseOptions){
        Optional<ResponseQuestion> existingRsponse = iResponseQuestionservice.findByID(id);

        if( existingRsponse.isPresent()){
            responseOptions.setId(id);

            ResponseQuestion updateResponse = iResponseQuestionservice.save(responseOptions);
            return  new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iResponseQuestionservice.findByID(id).isPresent()){
            iResponseQuestionservice.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


