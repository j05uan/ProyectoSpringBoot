package com.survey.survey.user.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.user.domain.entity.Users;

public interface IUserservice {

    Optional<Users> findByID(long id);
    List<Users> getAll();
    Users save(Users users);
    void deleteById(long id);
    Optional<Users> findByUsername(String username);

}
