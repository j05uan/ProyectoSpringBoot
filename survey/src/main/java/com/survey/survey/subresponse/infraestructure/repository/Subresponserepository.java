package com.survey.survey.subresponse.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.subresponse.domain.entity.Subresponse;

public interface Subresponserepository extends JpaRepository<Subresponse, Long>{

}
