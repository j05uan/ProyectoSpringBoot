package com.survey.survey.subresponse.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.subresponse.domain.entity.Subresponse;

public interface ISubresponseservice {

    Optional<Subresponse> findByID(long id);
    List<Subresponse> getAll();
    Subresponse save(Subresponse subresponse);
    void deleteById(long id);

}
