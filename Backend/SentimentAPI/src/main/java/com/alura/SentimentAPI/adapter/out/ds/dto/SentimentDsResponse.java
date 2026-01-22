package com.alura.SentimentAPI.adapter.out.ds.dto;

public record SentimentDsResponse(
        String comentario,
        String sentimiento,
        Double probabilidad
) {}

