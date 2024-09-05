package com.survey.survey.chapter.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.survey.chapter.application.service.IChapterservice;
import com.survey.survey.chapter.domain.entity.Chapter;
import com.survey.survey.chapter.domain.entity.ChapterUpdateDTO;

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

     public Chapter updateChapter(long id, ChapterUpdateDTO chapterUpdateDTO) {
        Optional<Chapter> optionalChapter = chapterepository.findById(id);
        if (optionalChapter.isPresent()) {
            Chapter chapter = optionalChapter.get();
            if (chapterUpdateDTO.getChapter_number() != null) {
                chapter.setChapter_number(chapterUpdateDTO.getChapter_number());
            }
            if (chapterUpdateDTO.getChapter_title() != null) {
                chapter.setChapter_title(chapterUpdateDTO.getChapter_title());
            }
            return chapterepository.save(chapter);
        }
        return null;
    }

}
