package com.survey.survey.responseOptions.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.responseOptions.domain.entity.ResponseOptions;

public interface IResponseOptionsservices {

    Optional<ResponseOptions> findByID(long id);
    List<ResponseOptions> getAll();
    ResponseOptions save(ResponseOptions responseOptions);
    void deleteById(long id);

}
