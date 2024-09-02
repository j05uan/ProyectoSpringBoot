package com.survey.survey.chapter.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.chapter.domain.entity.Chapter;

public interface Chapterepository extends JpaRepository<Chapter, Long>{

}
