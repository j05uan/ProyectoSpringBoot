package com.survey.survey.chapter.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.chapter.domain.entity.Chapter;
import com.survey.survey.chapter.domain.entity.ChapterUpdateDTO;

public interface IChapterservice {

    Optional<Chapter> findByID(long id);
    List<Chapter> getAll();
    Chapter save(Chapter chapter);
    void deleteById(long id);
    public Chapter updateChapter(long id, ChapterUpdateDTO chapterUpdateDTO);
}
