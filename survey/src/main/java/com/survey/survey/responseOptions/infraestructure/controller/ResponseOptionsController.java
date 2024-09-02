package com.survey.survey.responseOptions.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.responseOptions.application.service.IResponseOptionsservices;
import com.survey.survey.responseOptions.domain.entity.ResponseOptions;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.survey.survey.responseOptions.domain.entity.ResponseOptionsUpdateDTO;


@RestController
@RequestMapping("/api/response_options")
public class ResponseOptionsController {

    @Autowired
    private IResponseOptionsservices iResponseOptionsservices;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOptions> show(@PathVariable Long id){
        Optional<ResponseOptions> resposeOp = iResponseOptionsservices.findByID(id);
        return resposeOp.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<ResponseOptions> list(){
        return iResponseOptionsservices.getAll();
    }

    @PostMapping()
    public ResponseEntity<ResponseOptions> create (@RequestBody ResponseOptions entity) {
        iResponseOptionsservices.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseOptions> update(@PathVariable Long id, @RequestBody ResponseOptions responseOptions){
        Optional<ResponseOptions> existingRsponse = iResponseOptionsservices.findByID(id);

        if( existingRsponse.isPresent()){
            responseOptions.setId(id);

            ResponseOptions updateResponse = iResponseOptionsservices.save(responseOptions);
            return  new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseOptions> partialUpdate(@PathVariable Long id, @RequestBody ResponseOptionsUpdateDTO responseOptionsUpdateDTO){
        Optional<ResponseOptions> existingResponseOpt = iResponseOptionsservices.findByID(id);

        if (!existingResponseOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseOptions existingResponseOptions = existingResponseOpt.get();

        if(responseOptionsUpdateDTO.getOption_value() != null){
            existingResponseOptions.setOption_value(responseOptionsUpdateDTO.getOption_value());
        }
        if(responseOptionsUpdateDTO.getComment_response() != null){
            existingResponseOptions.setComment_response(responseOptionsUpdateDTO.getComment_response());
        }
        if(responseOptionsUpdateDTO.getOption_text() != null){
            existingResponseOptions.setOption_text(responseOptionsUpdateDTO.getOption_text());
        }
        ResponseOptions updatResponseOptions = iResponseOptionsservices.save(existingResponseOptions);
        return new ResponseEntity<>(updatResponseOptions, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iResponseOptionsservices.findByID(id).isPresent()){
            iResponseOptionsservices.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
