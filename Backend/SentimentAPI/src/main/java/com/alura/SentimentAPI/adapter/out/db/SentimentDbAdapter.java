package com.alura.SentimentAPI.adapter.out.db;

import com.alura.SentimentAPI.adapter.out.db.entity.SentimentEntity;
import com.alura.SentimentAPI.adapter.out.db.repository.SentimentJpaRepository;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SentimentDbAdapter implements SentimentRepositoryPort {

    private final SentimentJpaRepository repository;

    public SentimentDbAdapter(SentimentJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public void guardar(Sentiment sentiment, SentimentResult result) {

        SentimentEntity entity = new SentimentEntity(
                sentiment.getText(),
                result.label(),
                result.probability()
        );

        repository.save(entity);
    }


    @Override
    public void saveAll(Map<Sentiment, SentimentResult> lotes) {

        List<SentimentEntity> entities = lotes.entrySet().stream()
                .map(entry -> new SentimentEntity(
                        entry.getKey().getText(),
                        entry.getValue().label(),
                        entry.getValue().probability()
                ))
                .toList();

        repository.saveAll(entities);
    }

}
