package com.alura.SentimentAPI.domain.model;

public class SentimentLote {

    private String texto;
    private String prevision;
    private double probabilidad;
    private String idioma;

    public SentimentLote(String text, String label, double probability, String idioma) {
        this.texto = text;
        this.prevision = label;
        this.probabilidad = probability;
        this.idioma = idioma;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
