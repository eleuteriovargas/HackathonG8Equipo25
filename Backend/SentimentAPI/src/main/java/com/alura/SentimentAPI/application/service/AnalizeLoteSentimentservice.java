package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.adapter.in.rest.dto.LoteSentimentResponse;
import com.alura.SentimentAPI.application.usecase.AnalizeLoteSentimentUseCase;
import com.alura.SentimentAPI.domain.exception.InvalidSentimentTextException;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;

import java.util.*;

public class AnalizeLoteSentimentservice implements AnalizeLoteSentimentUseCase {

    private final SentimentAnalysisPort analyzePort;
    private final SentimentRepositoryPort repositoryPort;

    public AnalizeLoteSentimentservice(
            SentimentAnalysisPort analyzePort,
            SentimentRepositoryPort repositoryPort
    ) {
        this.analyzePort = analyzePort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public LoteSentimentResponse analizeAll(List<String> textos) {

        Map<Sentiment, SentimentResult> loteGuardar = new LinkedHashMap<>();

        int positivos = 0;
        int negativos = 0;
        int neutros = 0;

        for (String text : textos) {
            try {
                Sentiment sentiment = new Sentiment(text);
                SentimentResult result = analyzePort.analyze(sentiment.getText());

                loteGuardar.put(sentiment, result);

                switch (result.label().toLowerCase()) {
                    case "positivo" -> positivos++;
                    case "negativo" -> negativos++;
                    case "neutro" -> neutros++;
                }

                if (loteGuardar.size() == 50) {
                    repositoryPort.saveAll(loteGuardar);
                    loteGuardar.clear();
                }

            } catch (RuntimeException e) {
                throw new InvalidSentimentTextException(
                        "Error procesando el texto: " + text,
                        e
                );
            }
        }

        if (!loteGuardar.isEmpty()) {
            repositoryPort.saveAll(loteGuardar);
        }

        return new LoteSentimentResponse(
                positivos,
                negativos,
                neutros,
                textos.size()
        );
    }
}
