package com.survey.survey.roles.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.roles.domain.entity.Roles;

public interface Rolesrepository extends JpaRepository<Roles,Long> {

    List<Roles> findByNameIn(List<String> rolesnames);

}
