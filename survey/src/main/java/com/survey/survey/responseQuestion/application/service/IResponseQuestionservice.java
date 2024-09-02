package com.survey.survey.responseQuestion.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.responseQuestion.domain.entity.ResponseQuestion;

public interface IResponseQuestionservice {
    Optional<ResponseQuestion> findByID(long id);
    List<ResponseQuestion> getAll();
    ResponseQuestion save(ResponseQuestion responseQuestion);
    void deleteById(long id);
}
