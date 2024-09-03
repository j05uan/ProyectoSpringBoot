package com.survey.survey.subresponse.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.subresponse.application.service.ISubresponseservice;
import com.survey.survey.subresponse.domain.entity.Subresponse;

@Service
public class SubresponseAdapter  implements ISubresponseservice{

    @Autowired
    private Subresponserepository subresponserepository;

    @Override
    public Optional<Subresponse> findByID(long id){
        return subresponserepository.findById(id);
    }

    @Override
    public List<Subresponse> getAll(){
        return subresponserepository.findAll();
    }

    @Override
    public Subresponse save(Subresponse subresponse){
        return subresponserepository.save(subresponse);
    }

    @Override
    public void deleteById(long id){
        subresponserepository.deleteById(id);
    }

}
