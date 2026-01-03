package com.alura.SentimentAPI.application.usecase;

import com.alura.SentimentAPI.domain.model.SentimentResult;

public interface AnalyzeSentimentUseCase {
    SentimentResult analyze(String text);
}


