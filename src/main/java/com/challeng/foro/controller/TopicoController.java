package com.challeng.foro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*  La API debe tener un endpoint para el registro de nuevos tópicos y
    debe aceptar solicitudes POST para el URI /topicos.
    Los datos del tópico( titulo, mensaje, autor y curso) deben
    enviarse en el cuerpo de la solicitud, en formato JSON.
 */

@RestController("/topicos")
public class TopicoController {

<<<<<<< HEAD
//    @PostMapping
  //  public void nuevoTopico(topicoRepsitory newTopico) {

        
 //   }
=======
    @PostMapping
    public void nuevoTopico(topicoRepsitory newTopico) {

        
    }
>>>>>>> 894ea5cb0edfb17796a07703b13bd9b2fa021330
}
