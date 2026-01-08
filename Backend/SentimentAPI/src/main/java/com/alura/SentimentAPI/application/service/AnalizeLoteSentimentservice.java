package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.adapter.in.rest.dto.LoteSentimentResponse;
import com.alura.SentimentAPI.application.usecase.AnalizeLoteSentimentUseCase;
import com.alura.SentimentAPI.domain.exception.InvalidSentimentTextException;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalizeLoteSentimentservice implements AnalizeLoteSentimentUseCase {

    private final SentimentAnalysisPort analizePort;
    private final SentimentRepositoryPort repositoryPort;

    public AnalizeLoteSentimentservice(SentimentAnalysisPort analizePort, SentimentRepositoryPort repositoryPort) {
        this.analizePort = analizePort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public LoteSentimentResponse analizeAll(List<String> texto) {

        List<SentimentResult> resultadostotales = new ArrayList<>();
        Map<Sentiment, SentimentResult> loteGuardar = new LinkedHashMap<>();

        // estadisticos
        int positivos = 0;
        int negativos = 0;
        int neutros = 0;

        for (String text : texto) {
            try {
                Sentiment sentiment = new Sentiment(text);
                SentimentResult result = analizePort.analyze(sentiment);

                resultadostotales.add(result);
                loteGuardar.put(sentiment, result);

                // Sumar al contador a segun el tipo: positivo, negativo o neutro
                switch (result.getLabel().toLowerCase()) {
                    case "positivo" -> positivos++;
                    case "negativo" -> negativos++;
                    case "neutro" -> neutros++;
                }

                if (loteGuardar.size() == 50) {
                    repositoryPort.saveAll(loteGuardar);
                    loteGuardar.clear();
                }
            } catch (Exception e){
                throw new InvalidSentimentTextException("Error al guardar el LOTE");
            }
        }

        if (!loteGuardar.isEmpty()) {
            repositoryPort.saveAll(loteGuardar);
        }


        return new LoteSentimentResponse(
                positivos, negativos, neutros, texto.size()
        );
    }
}
