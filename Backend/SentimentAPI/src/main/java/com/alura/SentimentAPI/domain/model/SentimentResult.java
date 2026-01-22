package com.alura.SentimentAPI.domain.model;

public record SentimentResult(
        String label,
        Double probability
) {

}


