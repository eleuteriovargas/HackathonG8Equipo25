package com.alura.SentimentAPI.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SentimentValidos {
    @NotBlank
    private String text;
    @NotNull
    private String idioma;

    public SentimentValidos(String texto, String lenguaje) {
        this.text = texto;
        this.idioma = lenguaje;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
