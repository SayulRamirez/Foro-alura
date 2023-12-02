package com.challeng.foro.modelo;

import com.challeng.foro.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "curso")
@Entity(name = "Curso")
public class Curso {

    @Id
    private int id;

    private String nombreCurso;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}