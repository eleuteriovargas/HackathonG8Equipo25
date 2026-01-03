package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.application.exception.SentimentAnalysisFailedException;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;

public class AnalyzeSentimentService implements AnalyzeSentimentUseCase {

    private final SentimentAnalysisPort port;

    public AnalyzeSentimentService(SentimentAnalysisPort port) {
        this.port = port;
    }

    @Override
    public SentimentResult analyze(String text) {
        Sentiment sentiment = new Sentiment(text);
        return port.analyze(sentiment);
    }
}
