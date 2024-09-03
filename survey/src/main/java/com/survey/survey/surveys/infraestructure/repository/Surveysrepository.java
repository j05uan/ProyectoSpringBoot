package com.survey.survey.surveys.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.surveys.domain.entity.Surveys;

public interface Surveysrepository extends JpaRepository<Surveys, Long>{

}
