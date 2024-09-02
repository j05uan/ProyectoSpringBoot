package com.survey.survey.categories.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.survey.survey.categories.application.service.ICategoriesservices;
import com.survey.survey.categories.domain.entity.Categories;

public class CategoriesAdapter implements ICategoriesservices{

    @Autowired
    private Categoriesrepository categoriesrepository;

    @Override
    public Optional<Categories> findByID(long id){
        return categoriesrepository.findById(id);
    }

    @Override
    public List<Categories> getAll(){
        return categoriesrepository.findAll();
    }

    @Override
    public Categories save(Categories categories){
        return categoriesrepository.save(categories);
    }

    @Override
    public void deleteById(long id){
        categoriesrepository.deleteById(id);
    }

}
