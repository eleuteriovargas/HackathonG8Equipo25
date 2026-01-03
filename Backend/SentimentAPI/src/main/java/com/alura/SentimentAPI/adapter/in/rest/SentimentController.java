package com.alura.SentimentAPI.adapter.in.rest;

import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentRequest;
import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentResponse;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    private final AnalyzeSentimentUseCase useCase;

    public SentimentController(AnalyzeSentimentUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<SentimentResponse> analyze(
            @RequestBody @Valid SentimentRequest request
    ) {
        SentimentResult result = useCase.analyze(request.getText());

        return ResponseEntity.ok(
                new SentimentResponse(
                        result.getLabel(),
                        result.getProbability()
                )
        );
    }
}

