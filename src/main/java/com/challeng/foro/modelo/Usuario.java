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
<<<<<<< HEAD
@Embeddable
=======
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
@Table(name = "usuario")
@Entity(name = "Usuario")
public class Usuario {

<<<<<<< HEAD
    @Id
    private int id;

    private String nombre;

    private String email;

=======
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONTRASENA")
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
    private String contrasena;
}
