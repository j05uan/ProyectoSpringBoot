package com.survey.survey.useRole.domain.entities;

import com.survey.survey.roles.domain.entity.Roles;
import com.survey.survey.user.domain.entity.Users;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class UsersRoles {

    @EmbeddedId
    private userRoleId id;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Roles rol;

    @ManyToOne 
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users user;


}
