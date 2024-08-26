package com.survey.survey.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean enable;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    private String username;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;


}
