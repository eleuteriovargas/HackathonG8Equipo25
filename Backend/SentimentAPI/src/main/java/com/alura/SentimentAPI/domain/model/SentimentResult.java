package com.alura.SentimentAPI.domain.model;

public class SentimentResult {

    private final String label;
    private final double probability;

    public SentimentResult(String label, double probability) {
        this.label = label;
        this.probability = probability;
    }

    public String getLabel() {
        return label;
    }

    public double getProbability() {
        return probability;
    }
}


