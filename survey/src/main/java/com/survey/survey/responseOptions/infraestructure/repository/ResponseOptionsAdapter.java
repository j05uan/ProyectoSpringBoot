package com.survey.survey.responseOptions.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.survey.survey.responseOptions.application.service.IResponseOptionsservices;
import com.survey.survey.responseOptions.domain.entity.ResponseOptions;

public class ResponseOptionsAdapter implements IResponseOptionsservices{

    @Autowired
    private ResponseOptionsrepository responseOptionsrepository;

    @Override
    public Optional<ResponseOptions> findByID(long id){
        return responseOptionsrepository.findById(id);
    }

    @Override
    public List<ResponseOptions> getAll(){
        return responseOptionsrepository.findAll();
    }

    @Override
    public ResponseOptions save(ResponseOptions responseOptions){
        return responseOptionsrepository.save(responseOptions);
    }

    @Override 
    public void deleteById(long id){
        responseOptionsrepository.deleteById(id);
    }

}
