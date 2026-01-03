package com.alura.SentimentAPI.adapter.in.rest.dto;

public class SentimentResponse {

    private String prevision;
    private double probabilidad;

    public SentimentResponse(String prevision, double probabilidad) {
        this.prevision = prevision;
        this.probabilidad = probabilidad;
    }

    public String getPrevision() {
        return prevision;
    }

    public double getProbabilidad() {
        return probabilidad;
    }
}

