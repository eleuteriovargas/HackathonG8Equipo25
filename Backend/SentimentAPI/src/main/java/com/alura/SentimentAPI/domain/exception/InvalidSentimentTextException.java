package com.alura.SentimentAPI.domain.exception;

public class InvalidSentimentTextException extends RuntimeException {

    public InvalidSentimentTextException(String message) {
        super(message);
    }
}

