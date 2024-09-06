package com.survey.survey.user.domain.entity;

import java.util.HashSet;
import java.util.Set;

import com.survey.survey.roles.domain.entity.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean enable;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean isEnable;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean accountNoExpired;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean accountNoLocked;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns= @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();
}
