package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.application.exception.SentimentAnalysisFailedException;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import com.alura.SentimentAPI.domain.port.out.TraduccionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnalyzeSentimentService implements AnalyzeSentimentUseCase {

    private static final Logger log =
            LoggerFactory.getLogger(AnalyzeSentimentService.class);

    private static final double CONFIDENCE_THRESHOLD = 0.70;

    private final SentimentAnalysisPort analizePort;
    private final SentimentRepositoryPort repositoryPort;
    private final TraduccionPort traduccionPort;

    public AnalyzeSentimentService(SentimentAnalysisPort analizePort1, SentimentRepositoryPort repositoryPort1, TraduccionPort traduccionPort) {
        this.analizePort = analizePort1;
        this.repositoryPort = repositoryPort1;
        this.traduccionPort = traduccionPort;
    }

    public String limpiarTexto (String texto) {
//        if (texto == null) return "";

//        eliminar caracteres que no sean letras
        String limpio = texto.replace("[!¡?¿]+","");

        return limpio.trim();
    }

    @Override
    public SentimentResult analyze(String text, String idioma) {

        try {

//        Crear entidad dominio
            Sentiment sentiment = new Sentiment(text);

//      pasar a ingles el texto para el modelo entrenado
            String textTraducer = traduccionPort.traducir(text, idioma);

            Sentiment sentimentTraducido = new Sentiment(textTraducer);
//        llamar a Data Science
            SentimentResult rawResult = analizePort.analyze(sentimentTraducido);

            String finalLabel = rawResult.getLabel();
            double probability = rawResult.getProbability();

//        Regla de negocio: probabilidad alta
            if (probability < CONFIDENCE_THRESHOLD) {
                log.warn(
                        "Probabilidad baja ({}) para el texto '{}'. Forzando sentimiento NEUTRO",
                        probability,
                        text
                );
                finalLabel = "Neutro";

            }

            SentimentResult finalResult = new SentimentResult(finalLabel, probability);

            repositoryPort.guardar(sentiment, idioma, finalResult);

            return finalResult;
        } catch (Exception e) {
            log.error("Error analizando sentimiemto para el texto: {}", text, e);
            throw new SentimentAnalysisFailedException(
                    "No se pudo analizar el sentimiento",
                    e
            );
        }

    }
}
