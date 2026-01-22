package com.alura.SentimentAPI.domain.port.out;

import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;

public interface SentimentAnalysisPort {

    SentimentResult analyze(String text);

    SentimentResult analyze(Sentiment sentiment);
}

