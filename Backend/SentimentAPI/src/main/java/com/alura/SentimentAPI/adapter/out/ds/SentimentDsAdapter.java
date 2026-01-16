package com.alura.SentimentAPI.adapter.out.ds;

import com.alura.SentimentAPI.adapter.out.ds.dto.SentimentDsRequest;
import com.alura.SentimentAPI.adapter.out.ds.dto.SentimentDsResponse;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

public class SentimentDsAdapter implements SentimentAnalysisPort {

    private static final Logger log =
            LoggerFactory.getLogger(SentimentDsAdapter.class);

    private final WebClient webClient;

    /**
     * El adapter NO es @Component.
     * Se instancia desde BeanConfig.
     */
    public SentimentDsAdapter(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * Método principal usado por el caso de uso simple
     */
    @Override
    public SentimentResult analyze(String text) {

        log.info("Enviando texto a Data Science");

        SentimentDsRequest request = new SentimentDsRequest(text);

        SentimentDsResponse response = webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(SentimentDsResponse.class)
                .block();

        if (response == null) {
            log.error("Respuesta nula desde Data Science");
            throw new RuntimeException("Respuesta inválida desde Data Science");
        }

        log.info(
                "Respuesta DS: sentimiento={}, probabilidad={}",
                response.sentimiento(),
                response.probabilidad()
        );

        return new SentimentResult(
                response.sentimiento(),
                response.probabilidad()
        );
    }

    /**
     * Método usado cuando llega el objeto de dominio completo
     */
    @Override
    public SentimentResult analyze(Sentiment sentiment) {
        return analyze(sentiment.getText());
    }
}
