package com.survey.survey.security.controller.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;
@Validated
public record AuthCreateRoleRequest(@Size(max = 3, message="El usuario no puede tener mas de 3 roles") List<String> roleListName){
    
}
