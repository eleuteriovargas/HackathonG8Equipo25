package com.alura.SentimentAPI.adapter.out.db.repository;

import com.alura.SentimentAPI.adapter.out.db.entity.SentimentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentimentJpaRepository extends JpaRepository<SentimentEntity, Long> {
}
