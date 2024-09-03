package com.survey.survey.user.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.user.domain.entity.Users;

public interface Userrepository extends JpaRepository<Users,Long>{

}
