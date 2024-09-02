package com.survey.survey.question.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.question.domain.entity.Questions;

public interface IQuestionservice {

    Optional<Questions>findByID(long id);
    List<Questions> getAll();
    Questions save(Questions questions);
    void deleteById(long id);
    
}
