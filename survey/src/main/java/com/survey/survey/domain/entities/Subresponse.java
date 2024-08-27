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
@Table ( name = "subresponse_options")
public class Subresponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( columnDefinition = "INT(4)", nullable = false)
    private int subresponse_numbre;

    @Embedded
    private At at = new At();

    @ManyToOne
    @JoinColumn( name = "responseoptions_id")
    private ResponseOptions responseOptions;

    @Column( columnDefinition = "VARCHAR(255)", nullable = false)
    private String component_html;

    @Column( columnDefinition = "VARCHAR(255)", nullable = false)
    private String subresponse_text;
}
