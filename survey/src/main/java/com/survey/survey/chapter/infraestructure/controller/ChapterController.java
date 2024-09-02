package com.survey.survey.chapter.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.chapter.application.service.IChapterservice;
import com.survey.survey.chapter.domain.entity.Chapter;
import com.survey.survey.chapter.domain.entity.ChapterUpdateDTO;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {

    @Autowired
    private IChapterservice iChapterservice;

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> show(@PathVariable Long id) {
        Optional<Chapter> chapter = iChapterservice.findByID(id);
        return chapter.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Chapter>list(){
        return iChapterservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Chapter> create(@RequestBody Chapter chapter){
        iChapterservice.save(chapter);
        return new ResponseEntity<>(chapter,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chapter> update(@PathVariable Long id, @RequestBody Chapter chapter) {
    Optional<Chapter> existingChapter = iChapterservice.findByID(id);

    if (existingChapter.isPresent()) {
        chapter.setId(id);

        Chapter updatedChapter = iChapterservice.save(chapter);

        return new ResponseEntity<>(updatedChapter, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Chapter> partialUpdate(@PathVariable Long id, @RequestBody ChapterUpdateDTO chapterUpdateDTO) {
        Optional<Chapter> existingChapterOpt = iChapterservice.findByID(id);

        if (!existingChapterOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Chapter existingChapter = existingChapterOpt.get();

        if (chapterUpdateDTO.getChapter_number() != null) {
            existingChapter.setChapter_number(chapterUpdateDTO.getChapter_number());
        }
        if (chapterUpdateDTO.getChapter_title() != null) {
            existingChapter.setChapter_title(chapterUpdateDTO.getChapter_title());
        }

        Chapter updatedChapter = iChapterservice.save(existingChapter);
        return new ResponseEntity<>(updatedChapter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iChapterservice.findByID(id).isPresent()){
            iChapterservice.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
