package com.challeng.foro.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topicos")
@Entity(name = "Topicos")
public class Topicos {

    @Id
    private int id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private int estado;

    @Embedded
    private Usuario autor;

    @Embedded
    private Curso curso;
}








