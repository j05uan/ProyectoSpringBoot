package com.survey.survey.responseQuestion.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;

public interface ResponseQuestionrepository extends JpaRepository<ResponseQuestion, Long>{

}
