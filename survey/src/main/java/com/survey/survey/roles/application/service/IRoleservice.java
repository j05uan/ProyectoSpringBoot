package com.survey.survey.roles.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.roles.domain.entity.Roles;

public interface IRoleservice {
    Optional<Roles> findByID(long id);
    List<Roles> getAll();
    Roles save(Roles roles);
    void deleteById(Long id);

}
