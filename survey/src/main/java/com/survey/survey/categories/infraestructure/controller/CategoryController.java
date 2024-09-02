package com.survey.survey.categories.infraestructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.survey.categories.application.service.ICategoriesservices;
import com.survey.survey.categories.domain.entity.Categories;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoriesservices iCategoriesservices;

    @GetMapping
    public List<Categories> list(){
        return iCategoriesservices.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categories> show(@PathVariable Long id) {
        Optional<Categories> categories = iCategoriesservices.findByID(id);
        return categories.map( c -> new ResponseEntity<>(c, HttpStatus.OK))
            .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Categories> create(@RequestBody Categories category) {

        iCategoriesservices.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(@PathVariable Long id,
            @RequestBody Categories category) {

            Categories categoryUser = iCategoriesservices.save( category);
        return new ResponseEntity<>(categoryUser, HttpStatus.OK);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (iCategoriesservices.findByID(id).isPresent()) {
            iCategoriesservices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
