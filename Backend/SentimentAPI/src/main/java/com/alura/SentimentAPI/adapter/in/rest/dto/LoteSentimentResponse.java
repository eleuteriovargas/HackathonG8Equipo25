package com.alura.SentimentAPI.adapter.in.rest.dto;

import com.alura.SentimentAPI.adapter.out.db.entity.SentimentEntity;
import com.alura.SentimentAPI.domain.model.SentimentLote;
import com.alura.SentimentAPI.domain.model.SentimentResult;

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
