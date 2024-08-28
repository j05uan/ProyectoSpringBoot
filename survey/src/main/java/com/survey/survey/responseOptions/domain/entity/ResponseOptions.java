package com.survey.survey.responseOptions.domain.entity;


import com.survey.survey.At.domain.entity.At;
import com.survey.survey.categories.domain.entity.Categories;
import com.survey.survey.question.domain.entity.Questions;

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
@Table(name = "response_options")
public class ResponseOptions {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column( columnDefinition = "VARCHAR(10)", nullable = false)
    private String option_value;

    @ManyToOne
    @JoinColumn( name = "categorycatalog_id")
    private Categories categories;
    
    @Embedded
    private At at = new At();

    @ManyToOne
    @JoinColumn( name = "parentresponse_id")
    private ResponseOptions responseOptions;

    @ManyToOne
    @JoinColumn( name = "question_id")
    private Questions question;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String comment_response;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String option_text;
    
}
