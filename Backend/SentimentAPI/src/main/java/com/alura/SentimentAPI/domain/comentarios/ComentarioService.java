package com.alura.SentimentAPI.domain.comentarios;

import com.alura.SentimentAPI.domain.comentarios.validaciones.ValidadorDeComentarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final DsClient dsClient;
    public ComentarioService(DsClient dsClient) {
        this.dsClient = dsClient;
    }

//    @Autowired
//    private List<ValidadorDeComentarios> validadores;

//    public DatosComentarioResponse mostrar(DatosComentarioRequest datos) {
//
//        var datosExistente = verificarDatos(datos);
//        var texto = datos.texto();
//        System.out.println(datos.texto());
//        var comentario = new Comentario(null, datos, datosExistente.getPrevision(), datosExistente.getProbabilidad());
////        repository.save(comentario);
////        return new DatosDetalleProblema(comentario);
//
//        // validaciones
////        validadores.forEach(v -> v.validar(datos));
//
//        return new DatosComentarioResponse(comentario.getTexto(), comentario.getProbabilidad());
//    }

//    private Comentario verificarDatos(DatosComentarioRequest datos) {
//        return dsClient.verificarComentario(datos.texto());
//    }

}
