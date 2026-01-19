package com.alura.SentimentAPI.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TraduccionResponse(
        @JsonProperty("translatedText") String textoTraducido) {
}
