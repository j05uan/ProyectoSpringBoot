package com.survey.survey.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class userRoleId  implements Serializable{
    private Long role_id;
    private Long user_id;

    public userRoleId() {
    }
    public userRoleId(Long role_id, Long user_id) {
        this.role_id = role_id;
        this.user_id = user_id;
    }
    public Long getRole_id() {
        return role_id;
    }
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    
}
