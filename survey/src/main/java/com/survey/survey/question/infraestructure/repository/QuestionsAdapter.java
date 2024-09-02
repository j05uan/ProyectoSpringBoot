package com.survey.survey.question.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.survey.survey.question.application.service.IQuestionservice;
import com.survey.survey.question.domain.entity.Questions;

public class QuestionsAdapter implements IQuestionservice{

    @Autowired
    private Questionrepository questionrepository;

    @Override
    public Optional<Questions> findByID(long id){
        return questionrepository.findById(id);
    }

    @Override
    public List<Questions> getAll(){
        return questionrepository.findAll();
    }

    @Override
    public Questions save(Questions questions){
        return questionrepository.save(questions);
    }

    @Override
    public void deleteById(long id){
        questionrepository.deleteById(id);
    }

}
