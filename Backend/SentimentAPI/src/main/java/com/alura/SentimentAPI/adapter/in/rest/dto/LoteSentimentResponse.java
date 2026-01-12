package com.alura.SentimentAPI.adapter.in.rest.dto;

import com.alura.SentimentAPI.domain.model.SentimentResult;

import java.util.List;

public record LoteSentimentResponse(
        int totalPositivos,
        int totalNegativos,
        int totalneutros,
        int totalProcesados
) {
}
