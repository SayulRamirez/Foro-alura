package com.challeng.foro.modelo;

<<<<<<< HEAD
import com.challeng.foro.enums.Categoria;
=======
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
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
<<<<<<< HEAD
    private int id;

    private String nombreCurso;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
=======
    private int Id;

    @Column(name = "NOMBRE_CURSO")
    private String nombreCurso;

    @Column(name = "CATEGORIA")
    private int categoria;
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
}
