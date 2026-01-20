package com.alura.SentimentAPI.adapter.out.db;

import com.alura.SentimentAPI.adapter.out.db.entity.SentimentEntity;
import com.alura.SentimentAPI.adapter.out.db.repository.SentimentJpaRepository;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentLote;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SentimentDbAdapter implements SentimentRepositoryPort {

    private final SentimentJpaRepository repository;

    public SentimentDbAdapter(SentimentJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public void guardar(Sentiment sentiment, String idioma, SentimentResult result) {

        SentimentEntity entity = new SentimentEntity(
                sentiment.getText(),
                result.getLabel(),
                result.getProbability(),
                idioma
        );

        repository.save(entity);
    }

    @Override
    public void saveAll(List<SentimentLote> lotes) {

        List<SentimentEntity> entity = lotes.stream()
                .map(entry -> new SentimentEntity(
                        entry.getTexto(),
                        entry.getPrevision(),
                        entry.getProbabilidad(),
                        entry.getIdioma()
                )).toList();

        repository.saveAll(entity);

    }
}
