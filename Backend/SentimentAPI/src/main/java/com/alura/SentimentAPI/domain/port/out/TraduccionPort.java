package com.alura.SentimentAPI.domain.port.out;

import com.alura.SentimentAPI.domain.model.Sentiment;

public interface TraduccionPort {

    String traducir(String texto, String origen);
}
