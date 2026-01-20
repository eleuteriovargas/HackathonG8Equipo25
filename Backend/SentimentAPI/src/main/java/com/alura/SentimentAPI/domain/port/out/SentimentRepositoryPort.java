package com.alura.SentimentAPI.domain.port.out;

import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentLote;
import com.alura.SentimentAPI.domain.model.SentimentResult;

import java.util.List;

public interface SentimentRepositoryPort {
    void guardar(Sentiment sentiment, String idioma, SentimentResult result);

    // para guardar por lote
    void saveAll(List<SentimentLote> loteGuardar);
}
