package com.survey.survey.surveys.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.surveys.domain.entity.Surveys;

public interface ISurveyservice {

    Optional<Surveys> findByID(long id);
    List<Surveys> getAll();
    Surveys save(Surveys surveys);
    void deleteById(long id);

}
