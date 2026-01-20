package com.alura.SentimentAPI.adapter.in.rest.dto;

import com.alura.SentimentAPI.domain.model.SentimentLote;

import java.util.List;

public record LoteSentimentResponse(
        int totalPositivos,
        int totalNegativos,
        int totalneutros,
        int totalProcesados,
        int TotalNoProcesados,
        int totalComentarios,
        List<SentimentLote> Top5,
        List<SentimentLote> comentariosCriticos
) {
}
