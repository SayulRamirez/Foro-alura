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
@Embeddable
@Table(name = "usuario")
@Entity(name = "Usuario")
public class Usuario {

    @Id
    private int id;

    private String nombre;

    private String email;

    private String contrasena;
}
