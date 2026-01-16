package com.alura.SentimentAPI.adapter.in.rest.dto;

public class SentimentResponse {

    private String texto;
    private String sentimiento;
    private double probabilidad;

    public SentimentResponse(String texto, String sentimiento, double probabilidad) {
        this.texto = texto;
        this.sentimiento = sentimiento;
        this.probabilidad = probabilidad;
    }

    public String getTexto() {
        return texto;
    }

    public String getSentimiento() {
        return sentimiento;
    }

    public double getProbabilidad() {
        return probabilidad;
    }
}

