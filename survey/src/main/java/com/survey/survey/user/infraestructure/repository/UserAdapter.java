package com.survey.survey.user.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.user.application.service.IUserservice;
import com.survey.survey.user.domain.entity.Users;

@Service
public class UserAdapter implements IUserservice{

    @Autowired
    private Userrepository userrepository;

    @Override
    public Optional<Users> findByID(long id){
        return userrepository.findById(id);
    }

    @Override
    public List<Users> getAll(){
        return userrepository.findAll();
    }

    @Override
    public Users save(Users users){
        return userrepository.save(users);
    }

    @Override 
    public void deleteById(long id){
        userrepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String username){
        return  userrepository.findByUsername(username);
    }

}
