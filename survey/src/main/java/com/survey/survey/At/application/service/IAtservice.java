package com.survey.survey.At.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.At.domain.entity.At;


public interface IAtservice {

    Optional<At> findByID(long Id);
    List<At> getAll();
    At save (At at);
    void  deleteById(long  id);
    
    // At updateById(At at);
}
