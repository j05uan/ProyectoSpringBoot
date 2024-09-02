package com.survey.survey.chapter.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.chapter.domain.entity.Chapter;

public interface IChapterservice {

    Optional<Chapter> findByID(long id);
    List<Chapter> getAll();
    Chapter save(Chapter chapter);
    void deleteById(long id);

}
