package com.alura.SentimentAPI.adapter.in.rest.dto;

public class SentimentResponse {

    private String comentario;
    private String sentimiento;
    private double probabilidad;

    public SentimentResponse(String comentario, String sentimiento, double probabilidad) {
        this.comentario = comentario;
        this.sentimiento = sentimiento;
        this.probabilidad = probabilidad;
    }

    public String getComentario() {
        return comentario;
    }

    public String getSentimiento() {
        return sentimiento;
    }

    public double getProbabilidad() {
        return probabilidad;
    }
}

