package com.challeng.foro.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "curso")
@Entity(name = "Curso")
public class Curso {

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int Id;

    @Column(name = "NOMBRE_CURSO")
    String nombre_curso;

    @Column(name = "CATEGORIA")
    int categoria;
}
