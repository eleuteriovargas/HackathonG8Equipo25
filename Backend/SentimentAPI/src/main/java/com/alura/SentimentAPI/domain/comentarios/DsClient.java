package com.alura.SentimentAPI.domain.comentarios;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class DsClient {

//    private final WebClient webClient;

//    public DsClient(WebClient)

    public Comentario verificarComentario(@NotBlank String texto) {
        return new Comentario();
    }
}
