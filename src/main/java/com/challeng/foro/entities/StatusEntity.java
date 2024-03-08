package com.challeng.foro.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "status")
public class StatusEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15)
    private String status;

    public StatusEntity() {}

    public StatusEntity(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
}
