package com.survey.survey.user.infraestructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.survey.user.domain.entity.Users;
@Repository
public interface Userrepository extends JpaRepository<Users,Long>{

    // @Query("SELECT u FROM User u WHERE u.username = :username")
    // Optional<Users> findUserByUsername(@Param("username") String username);

    Optional<Users> findByUsername(String username);
}
