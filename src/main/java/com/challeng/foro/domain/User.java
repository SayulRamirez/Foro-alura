package com.challeng.foro.domain;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {

    private Long id;

    @Size(min = 10, max = 30, message = "It should contain between 10 and 30 charactes")
    @NotEmpty(message = "The field must be fielled in")
    private String name;

    public User(){}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
