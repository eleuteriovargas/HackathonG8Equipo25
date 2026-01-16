package com.alura.SentimentAPI.domain.comentarios;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentimentMelvin")
public class ComentarioController {

//    @Autowired
//    private ComentarioService service;
//
//    @PostMapping
//    public ResponseEntity<DatosComentarioResponse> verificar(@RequestBody @Valid DatosComentarioRequest datos) {
//
//        var detalleComentario = service.mostrar(datos);
//
//        return ResponseEntity.ok(detalleComentario);
//    }

//    @PostMapping("/csv")
//    public String verificarCSV(@RequestBody @Valid DatosComentarioRequest datos) {
//
//        System.out.println(datos.texto());
//
//        return "Si funciona" + datos.texto();
//
//        var detalleComentario = service.mostrar(datos);
//
//        return ResponseEntity.ok(detalleComentario);
//    }

}
