package com.survey.survey.At.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.survey.survey.At.application.service.IAtservice;
import com.survey.survey.At.domain.entity.At;

public class AtAdapter implements  IAtservice {

    @Autowired
    private Atrepository atrepository;

    @Override
    public Optional<At> findByID(long id){
        return atrepository.findById(id);
    }

    @Override
    public List<At> getAll(){
        return atrepository.findAll();
    }

    @Override
    public At save(At at){
        return atrepository.save(at);
    }

    @Override
    public void  deleteById(long id){
        atrepository.deleteById(id);
    }


    //El metodo save retorna el objeto
    // @Override
    // public At updateById(At at){
    //     return  atrepository.save(at);
    // }
}
