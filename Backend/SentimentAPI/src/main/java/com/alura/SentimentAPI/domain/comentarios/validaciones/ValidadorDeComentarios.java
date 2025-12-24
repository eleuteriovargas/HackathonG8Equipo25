package com.alura.SentimentAPI.domain.comentarios.validaciones;

import com.alura.SentimentAPI.domain.comentarios.DatosComentarioRequest;

public interface ValidadorDeComentarios {
    void validar(DatosComentarioRequest datos);
}
