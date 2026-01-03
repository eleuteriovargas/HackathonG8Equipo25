package com.alura.SentimentAPI.adapter.out.ds;

import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.adapter.out.ds.exception.DataScienceServiceException;


public class SentimentDsAdapter implements SentimentAnalysisPort {

    @Override
    public SentimentResult analyze(Sentiment sentiment) {
        try {
            // Aquí irá la llamada HTTP real
            return new SentimentResult("Positivo", 0.82);
        } catch (Exception ex) {
            throw new DataScienceServiceException(
                    "No se pudo comunicar con el servicio de Data Science"
            );
        }
    }
}


