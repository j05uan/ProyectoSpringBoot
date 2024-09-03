package com.survey.survey.roles.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.roles.application.service.IRoleservice;
import com.survey.survey.roles.domain.entity.Roles;

@Service
public class RolesAdapter implements IRoleservice {

    @Autowired
    private Rolesrepository rolesrepository;

    @Override
    public Optional<Roles> findByID(long id){
        return rolesrepository.findById(id);
    }

    @Override
    public List<Roles> getAll(){
        return rolesrepository.findAll();
    }

    @Override
    public Roles save(Roles roles){
        return rolesrepository.save(roles);
    }

    @Override
    public void deleteById(Long id) {
        rolesrepository.deleteById(id);
    }

}
