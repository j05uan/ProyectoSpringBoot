package com.survey.survey.chapter.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.chapter.application.service.IChapterservice;
import com.survey.survey.chapter.domain.entity.Chapter;

@Service
public class ChapterAdapter  implements IChapterservice {

    @Autowired
    private Chapterepository chapterepository;

    @Override
    public Optional<Chapter> findByID(long id){
        return chapterepository.findById(id);
    }

    @Override
    public List<Chapter> getAll(){
        return chapterepository.findAll();
    }

    @Override
    public Chapter save(Chapter chapter){
        return chapterepository.save(chapter);
    }

    @Override
    public void deleteById(long id){
        chapterepository.deleteById(id);
    }


}
