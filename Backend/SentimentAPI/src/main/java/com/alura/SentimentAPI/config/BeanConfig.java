package com.alura.SentimentAPI.config;

import com.alura.SentimentAPI.adapter.out.db.SentimentDbAdapter;
import com.alura.SentimentAPI.adapter.out.ds.SentimentDsAdapter;
import com.alura.SentimentAPI.application.service.AnalizeLoteSentimentservice;
import com.alura.SentimentAPI.application.service.AnalyzeSentimentService;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SentimentAnalysisPort sentimentAnalysisPort() {
        return new SentimentDsAdapter();
    }

    @Bean
    public AnalyzeSentimentUseCase analyzeSentimentUseCase(
            SentimentAnalysisPort analysisPort,
            SentimentRepositoryPort repositoryPort
    ) {
        return new AnalyzeSentimentService(analysisPort, repositoryPort);
    }

    @Bean
    public AnalizeLoteSentimentservice analyzeLoteSentimentUseCase(
            SentimentAnalysisPort port,
            SentimentRepositoryPort repository
    ) {
        return new AnalizeLoteSentimentservice(port, repository);
    }
}

