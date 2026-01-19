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

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }
}

