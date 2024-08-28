package com.survey.survey.question.domain.entity;

import com.survey.survey.At.domain.entity.At;
import com.survey.survey.chapter.domain.entity.Chapter;

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
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private At at = new At();

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Column( columnDefinition = "VARCHAR(10)", nullable = false)
    private String question_number;

    @Column( columnDefinition = "VARCHAR(20)", nullable = false)
    private String response_type;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String comment_question;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String question_text;
    

}
