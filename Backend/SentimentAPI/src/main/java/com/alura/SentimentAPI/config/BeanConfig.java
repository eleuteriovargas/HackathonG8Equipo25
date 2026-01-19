package com.alura.SentimentAPI.config;

import com.alura.SentimentAPI.adapter.out.db.SentimentDbAdapter;
import com.alura.SentimentAPI.adapter.out.ds.SentimentDsAdapter;
import com.alura.SentimentAPI.application.service.AnalizeLoteSentimentservice;
import com.alura.SentimentAPI.application.service.AnalyzeSentimentService;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import com.alura.SentimentAPI.domain.port.out.TraduccionPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SentimentAnalysisPort sentimentAnalysisPort(
            @Value("${ds.base-url}") String baseurl
    ) {
        return new SentimentDsAdapter(baseurl);
    }

    @Bean
    public AnalyzeSentimentUseCase analyzeSentimentUseCase(
            SentimentAnalysisPort analysisPort,
            SentimentRepositoryPort repositoryPort,
            TraduccionPort traduccionPort
    ) {
        return new AnalyzeSentimentService(analysisPort, repositoryPort, traduccionPort);
    }

    @Bean
    public AnalizeLoteSentimentservice analyzeLoteSentimentUseCase(
            SentimentAnalysisPort port,
            SentimentRepositoryPort repository,
            TraduccionPort traduccionPort
    ) {
        return new AnalizeLoteSentimentservice(port, repository, traduccionPort);
    }
}

