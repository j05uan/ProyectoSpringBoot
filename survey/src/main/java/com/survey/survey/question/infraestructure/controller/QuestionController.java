package com.survey.survey.question.infraestructure.controller;

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

import com.survey.survey.question.application.service.IQuestionservice;
import com.survey.survey.question.domain.entity.QuestionUpdateDTO;
import com.survey.survey.question.domain.entity.Questions;


@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private IQuestionservice iQuestionservice;

    @GetMapping("/{id}")
    public ResponseEntity<Questions> show(@PathVariable Long id){
        Optional<Questions> questions = iQuestionservice.findByID(id);
        return  questions.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public List<Questions> list(){
        return iQuestionservice.getAll();
    }

    @PostMapping
    public ResponseEntity<Questions> create(@RequestBody Questions questions){
        iQuestionservice.save(questions);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questions> update(@PathVariable Long id, @RequestBody Questions questions){
        Optional<Questions> existingQuestion = iQuestionservice.findByID(id);

        if (existingQuestion.isPresent()) {
            questions.setId(id);
            
            Questions updateQuestions = iQuestionservice.save(questions);
            return  new ResponseEntity<>(updateQuestions, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Questions> partialUpdate(@PathVariable Long id,@RequestBody QuestionUpdateDTO questionsDTO){
        Optional<Questions> existingQuestionOpt = iQuestionservice.findByID(id);

        if (!existingQuestionOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Questions existQuestions = existingQuestionOpt.get();

        if (questionsDTO.getResponse_type() != null) {
            existQuestions.setResponse_type(questionsDTO.getResponse_type());
        }
        if (questionsDTO.getComment_question() != null) {
            existQuestions.setComment_question(questionsDTO.getComment_question());
        }
        if (questionsDTO.getQuestion_text() != null) {
            existQuestions.setQuestion_text(questionsDTO.getQuestion_text());
        }
        Questions updateQuestion = iQuestionservice.save(existQuestions);
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iQuestionservice.findByID(id).isPresent()){
            iQuestionservice.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

