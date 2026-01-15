package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.application.exception.SentimentAnalysisFailedException;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;


public class AnalyzeSentimentService implements AnalyzeSentimentUseCase {

    private final SentimentAnalysisPort analizePort;
    private final SentimentRepositoryPort repositoryPort;

    public AnalyzeSentimentService(SentimentAnalysisPort analizePort1, SentimentRepositoryPort repositoryPort1) {
        this.analizePort = analizePort1;
        this.repositoryPort = repositoryPort1;
    }

    @Override
    public SentimentResult analyze(String text) {
        Sentiment sentiment = new Sentiment(text);

        SentimentResult result = analizePort.analyze(sentiment);

        repositoryPort.guardar(sentiment, result);

        return result;
    }
}
