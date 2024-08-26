package com.survey.survey.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table (name = "survey_json")
public class SurveyJson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Embedded
    private At at = new At();

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Surveys survey;


    @Column(columnDefinition = "JSON", nullable = false)
    private String jsonstring = "";

    
}
