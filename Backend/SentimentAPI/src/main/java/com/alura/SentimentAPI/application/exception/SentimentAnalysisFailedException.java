package com.alura.SentimentAPI.application.exception;

public class SentimentAnalysisFailedException extends RuntimeException {

    public SentimentAnalysisFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

