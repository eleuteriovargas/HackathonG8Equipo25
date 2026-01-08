package com.alura.SentimentAPI.domain.port.out;

import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;

import java.util.Map;

public interface SentimentRepositoryPort {
    void guardar(Sentiment sentiment, SentimentResult result);
    // para guardar por lote
    void saveAll(Map<Sentiment, SentimentResult> lotes);
}
