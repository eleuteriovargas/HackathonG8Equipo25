package com.alura.SentimentAPI.adapter.in.rest;

import com.alura.SentimentAPI.adapter.in.rest.dto.LoteSentimentResponse;
import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentLoteRequest;
import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentRequest;
import com.alura.SentimentAPI.adapter.in.rest.dto.SentimentResponse;
import com.alura.SentimentAPI.application.usecase.AnalizeLoteSentimentUseCase;
import com.alura.SentimentAPI.application.usecase.AnalyzeSentimentUseCase;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sentiment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SentimentController {

    private final AnalyzeSentimentUseCase useCase;
    private final AnalizeLoteSentimentUseCase loteCase;

    public SentimentController(AnalyzeSentimentUseCase useCase, AnalizeLoteSentimentUseCase loteCase) {
        this.useCase = useCase;
        this.loteCase = loteCase;
    }

    @PostMapping
    public ResponseEntity<SentimentResponse> analyze(
            @RequestBody @Valid SentimentRequest request
    ) {
        SentimentResult result = useCase.analyze(request.getText(), request.getIdioma());

        return ResponseEntity.ok(
                new SentimentResponse(
                        request.getText(),
                        result.getLabel(),
                        result.getProbability() * 100
                )
        );
    }

    @PostMapping("/csv")
    public ResponseEntity<LoteSentimentResponse> analizeLote(@RequestBody List<SentimentLoteRequest> requests) {


        LoteSentimentResponse response = loteCase.analizeAll(requests);

        return ResponseEntity.ok(response);
    }


}

