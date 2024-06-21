package com.maider.article.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique=true)
    private String username;
    @NotEmpty
    private String[] roles;

    public User(Long id, String username,  String[] roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public User(String username, @NotBlank String[] roles) {
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = username;
    }

    public  String[] getRoles() {
        return roles;
    }

    public void setRole( String[] roles) {
        this.roles = roles;
    }
}
