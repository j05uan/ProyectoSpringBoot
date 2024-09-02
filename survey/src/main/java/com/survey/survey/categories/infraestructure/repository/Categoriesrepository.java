package com.survey.survey.categories.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.survey.categories.domain.entity.Categories;

public interface Categoriesrepository extends JpaRepository<Categories, Long>{

}
