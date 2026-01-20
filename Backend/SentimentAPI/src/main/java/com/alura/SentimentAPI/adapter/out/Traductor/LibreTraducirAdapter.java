package com.alura.SentimentAPI.adapter.out.Traductor;

import com.alura.SentimentAPI.adapter.in.rest.dto.TraduccionResponse;
import com.alura.SentimentAPI.domain.port.out.TraduccionPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class LibreTraducirAdapter implements TraduccionPort {

    private final WebClient client = WebClient.create("http://localhost:5000");

    @Override
    public String traducir(String texto, String idiomaOrigen) {
        String repuestaJson = client.post()
                .uri("/translate")
                .bodyValue(Map.of(
                        "q", texto,
                        "source", idiomaOrigen, // Aqui pasas "es" o "pt"
                        "target", "en",         // Siempre a ingles para el modelo
                        "format", "text"
                ))
                .retrieve()
                .bodyToMono(TraduccionResponse.class)
                .map(TraduccionResponse::textoTraducido)
                .block();

        return repuestaJson;

    }

}
