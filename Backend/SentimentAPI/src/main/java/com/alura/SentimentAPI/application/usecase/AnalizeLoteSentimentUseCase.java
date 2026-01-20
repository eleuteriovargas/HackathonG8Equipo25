package com.alura.SentimentAPI.application.usecase;

import com.alura.SentimentAPI.adapter.in.rest.dto.LoteSentimentResponse;
import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentLoteRequest;

import java.util.List;

public interface AnalizeLoteSentimentUseCase {

    LoteSentimentResponse analizeAll(List<SentimentLoteRequest> texto);
}
