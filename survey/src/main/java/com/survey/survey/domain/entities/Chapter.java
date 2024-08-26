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
@Table( name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Surveys survey;

    @Embedded
    private At at = new At();

    @Column( columnDefinition = "VARCHAR(50)", nullable = false)
    private String chapter_number;
    @Column( columnDefinition = "VARCHAR(50)", nullable = false)
    private String chapter_title;


}
