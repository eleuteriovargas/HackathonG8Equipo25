package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.application.exception.SentimentAnalysisFailedException;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyzeSentimentService implements AnalyzeSentimentUseCase {

    private static final Logger log =
            LoggerFactory.getLogger(AnalyzeSentimentService.class);


    private static final double CONFIDENCE_THRESHOLD = 0.70;

    private final SentimentAnalysisPort analysisPort;
    private final SentimentRepositoryPort repositoryPort;

    public AnalyzeSentimentService(
            SentimentAnalysisPort analysisPort,
            SentimentRepositoryPort repositoryPort
    ) {
        this.analysisPort = analysisPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public SentimentResult analyze(String text) {

        try {
            //  Crear entidad de dominio
            Sentiment sentiment = new Sentiment(text);

            //  Llamar a Data Science
            SentimentResult rawResult = analysisPort.analyze(sentiment);

            String finalLabel = rawResult.label();
            double probability = rawResult.probability();

            //  Regla de negocio: probabilidad alta
            if (probability < CONFIDENCE_THRESHOLD) {
                log.warn(
                        "Probabilidad baja ({}) para el texto '{}'. Forzando sentimiento NEUTRO",
                        probability,
                        text
                );
                finalLabel = "Neutro";
            }

            SentimentResult finalResult =
                    new SentimentResult(finalLabel, probability);

            // Persistir resultado
            repositoryPort.guardar(sentiment, finalResult);

            return finalResult;

        } catch (Exception e) {
            log.error("Error analizando sentimiento para el texto: {}", text, e);
            throw new SentimentAnalysisFailedException(
                    "No se pudo analizar el sentimiento",
                    e
            );
        }
    }
}
