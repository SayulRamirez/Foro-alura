package com.challeng.foro.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respuesta")
@Entity(name = "Respuesta")
public class Respuesta {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MENSAJE")
    private String mensaje;

    @Column(name = "TOPICO")
    private int topico;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "AUTOR")
    private int autor;

    @Column(name = "SOLUCION", columnDefinition = "TINYINT(1)")
    private boolean solucion = false;
}
