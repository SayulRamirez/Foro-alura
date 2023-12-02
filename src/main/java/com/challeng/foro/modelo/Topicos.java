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
<<<<<<< HEAD
    private int id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private int estado;

    @Embedded
    private Usuario autor;

    @Embedded
    private Curso curso;
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "MENSAJE")
    private String mensaje;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "ESTADO")
    private int estado;

    @Column(name = "AUTOR")
    private int autor;

    @Column(name = "CURSO")
    private int curso;
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
}








