package com.alura.SentimentAPI.adapter.in.rest.exception;
import com.alura.SentimentAPI.application.exception.SentimentAnalysisFailedException;
import com.alura.SentimentAPI.domain.exception.InvalidSentimentTextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSentimentTextException.class)
    public ResponseEntity<?> handleInvalidText(InvalidSentimentTextException ex) {
        return ResponseEntity.badRequest().body(
                error(HttpStatus.BAD_REQUEST, ex.getMessage())
        );
    }

    @ExceptionHandler(SentimentAnalysisFailedException.class)
    public ResponseEntity<?> handleAnalysisError(SentimentAnalysisFailedException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                error(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado")
        );
    }

    private Map<String, Object> error(HttpStatus status, String message) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMissingBody(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                error(HttpStatus.BAD_REQUEST, "El cuerpo de la petición es obligatorio")
        );
    }


}

