package com.survey.survey.responseQuestion.domain.entity;

import com.survey.survey.responseOptions.domain.entity.ResponseOptions;
import com.survey.survey.subresponse.domain.entity.Subresponse;

import jakarta.persistence.Column;
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
@Table( name = "response_question")
public class ResponseQuestion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn( name = "response_id")
    private ResponseOptions response;

    @ManyToOne
    @JoinColumn( name = "subresponses_id")
    private Subresponse subresponse;

    @Column( columnDefinition = "VARCHAR(80)", nullable = false)
    private String responsetext;

}
