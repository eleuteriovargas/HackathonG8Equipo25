package com.alura.SentimentAPI.adapter.in.rest.dto;

import com.alura.SentimentAPI.domain.model.SentimentLote;

import java.util.List;

public record LoteSentimentResponse(
        int totalPositivos,
        int totalNegativos,
        int totalNeutros,
        int totalNoProcesados,
        int totalComentarios,
        int totalProcesados,
        List<SentimentLote> Top5,
        List<SentimentLote> comentariosCriticos
) {
}
