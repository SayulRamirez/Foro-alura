package com.challeng.foro.domain;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {

    private Long id;

    @Size(min = 10, max = 30, message = "It should contain between 10 and 30 charactes")
    @NotEmpty(message = "The field must be fielled in")
    private String name;

    @Size(min = 10, max = 20, message = "It should contain between 10 and 20 charactes")
    @NotEmpty(message = "The field must be fielled in")
    private String email;

    @Size(min = 8, max = 15, message = "It should contain between 8 and 15 characters")
    @NotEmpty(message = "The field must be fielled in")
    private String password;
    public User(){}

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
