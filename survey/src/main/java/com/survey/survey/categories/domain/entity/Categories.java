package com.survey.survey.categories.domain.entity;

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
@Table(name = "categories_catalog")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private At at = new At();
    
    @Column( columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    // @OneToMany
    // @JoinColumn( name = "categorycatalog_id", nullable = false)
    // private ResponseOptions responseOptions;

}
