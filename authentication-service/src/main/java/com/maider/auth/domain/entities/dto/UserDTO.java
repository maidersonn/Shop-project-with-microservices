package com.maider.auth.domain.entities.dto;

public class UserDTO {
    private String username;
    private Long id;
    private String[] roles;

    public UserDTO(String username, Long id, String[] roles) {
        this.username = username;
        this.id = id;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
