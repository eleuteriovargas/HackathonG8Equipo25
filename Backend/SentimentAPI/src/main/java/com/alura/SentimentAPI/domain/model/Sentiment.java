package com.alura.SentimentAPI.domain.model;

import com.alura.SentimentAPI.domain.exception.InvalidSentimentTextException;

public class Sentiment {

    private final String text;

    public Sentiment(String text) {
        if (text == null || text.trim().length() < 5) {
            throw new InvalidSentimentTextException(
                    "El texto debe tener al menos 5 caracteres"
            );
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }
}



