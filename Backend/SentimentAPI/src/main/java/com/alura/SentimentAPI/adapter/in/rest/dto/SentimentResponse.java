package com.alura.SentimentAPI.adapter.in.rest.dto;

public class SentimentResponse {

    private String texto;
    private String prevision;
    private double probabilidad;

    public SentimentResponse(String texto, String prevision, double probabilidad) {
        this.texto = texto;
        this.prevision = prevision;
        this.probabilidad = probabilidad;
    }

    public String getTexto() {
        return texto;
    }

    public String getPrevision() {
        return prevision;
    }

    public double getProbabilidad() {
        return probabilidad;
    }
}

