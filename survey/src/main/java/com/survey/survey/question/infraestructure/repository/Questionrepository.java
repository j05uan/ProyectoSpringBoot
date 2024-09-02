package com.survey.survey.question.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.question.domain.entity.Questions;

public interface Questionrepository extends JpaRepository<Questions, Long>{

}
