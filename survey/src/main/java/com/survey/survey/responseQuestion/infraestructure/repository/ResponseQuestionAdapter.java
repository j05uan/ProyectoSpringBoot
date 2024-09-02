package com.survey.survey.responseQuestion.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.responseQuestion.application.service.IResponseQuestionservice;
import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;

@Service
public class ResponseQuestionAdapter implements IResponseQuestionservice{

    @Autowired
    private ResponseQuestionrepository responseQuestionrepository;

    @Override
    public Optional<ResponseQuestion> findByID(long id){
        return responseQuestionrepository.findById(id);
    }

    @Override
    public List<ResponseQuestion> getAll(){
        return responseQuestionrepository.findAll();
    }

    @Override
    public ResponseQuestion save( ResponseQuestion responseQuestion){
        return  responseQuestionrepository.save(responseQuestion);
    }

    @Override
    public void deleteById(long id){
        responseQuestionrepository.deleteById(id);
    }
}
