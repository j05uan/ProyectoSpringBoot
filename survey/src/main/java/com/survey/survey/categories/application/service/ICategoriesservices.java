package com.survey.survey.categories.application.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.categories.domain.entity.Categories;

public interface ICategoriesservices {
    Optional<Categories> findByID(long id);
    List<Categories> getAll();
    Categories save(Categories categories);
    void deleteById(long id);

}
