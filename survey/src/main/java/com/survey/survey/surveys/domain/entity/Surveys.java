package com.survey.survey.surveys.domain.entity;

import com.survey.survey.At.domain.entity.At;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "surveys")
public class Surveys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private At at = new At();

    @Column (columnDefinition = "VARCHAR(255)", nullable = false)
    private String Description;

    @Column (columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

}
