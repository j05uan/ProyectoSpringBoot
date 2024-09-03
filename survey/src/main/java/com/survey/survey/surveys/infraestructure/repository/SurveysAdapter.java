package com.survey.survey.surveys.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.surveys.application.service.ISurveyservice;
import com.survey.survey.surveys.domain.entity.Surveys;

@Service
public class SurveysAdapter implements ISurveyservice {

    @Autowired
    private Surveysrepository surveysrepository;

    @Override
    public Optional<Surveys> findByID(long id){
        return surveysrepository.findById(id);
    }

    @Override
    public List<Surveys> getAll(){
        return surveysrepository.findAll();
    }

    @Override
    public Surveys save(Surveys surveys){
        return surveysrepository.save(surveys);
    }

    @Override
    public void deleteById(long id){
        surveysrepository.deleteById(id);
    }

}
