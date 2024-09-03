package com.survey.survey.surveys.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.chapter.domain.entity.Chapter;
import com.survey.survey.subresponse.application.service.ISubresponseservice;
import com.survey.survey.surveys.application.service.ISurveyservice;
import com.survey.survey.surveys.domain.entity.Surveys;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/surveys")
public class SurveysControllers {

    @Autowired
    private ISurveyservice iSurveyservice;

    @GetMapping("/{id}")
    public ResponseEntity<Surveys> show(@PathVariable Long id){
        Optional<Surveys> survey = iSurveyservice.findByID(id);
        return survey.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Surveys> list(){
        return iSurveyservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Surveys> create(@RequestBody Surveys entity) {
        iSurveyservice.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Surveys> update(@PathVariable Long id, @RequestBody Surveys entity) {
        Optional<Surveys> existingSurveys = iSurveyservice.findByID(id);
        
        if (existingSurveys.isPresent()) {
            entity.setId(id);
    
            Surveys updatedSurveys= iSurveyservice.save(entity);
    
            return new ResponseEntity<>(updatedSurveys, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iSurveyservice.findByID(id).isPresent()){
            iSurveyservice.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

}
