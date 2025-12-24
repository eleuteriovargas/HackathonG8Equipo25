package com.alura.SentimentAPI.domain.comentarios;


import jakarta.validation.constraints.NotBlank;

public record DatosComentarioRequest(
        @NotBlank String texto
) {
}
