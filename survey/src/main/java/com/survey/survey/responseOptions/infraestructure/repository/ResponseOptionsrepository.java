package com.survey.survey.responseOptions.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.responseOptions.domain.entity.ResponseOptions;

public interface ResponseOptionsrepository extends JpaRepository<ResponseOptions, Long>{

}
