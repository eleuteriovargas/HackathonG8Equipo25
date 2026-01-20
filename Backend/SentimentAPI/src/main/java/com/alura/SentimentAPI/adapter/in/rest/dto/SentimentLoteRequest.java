package com.alura.SentimentAPI.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public class SentimentLoteRequest {
    @NotBlank
    private String text;
    @NotBlank
    private String idioma;

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
