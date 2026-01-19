package com.alura.SentimentAPI.application.service;

import com.alura.SentimentAPI.adapter.in.rest.dto.*;
import com.alura.SentimentAPI.application.usecase.AnalizeLoteSentimentUseCase;
import com.alura.SentimentAPI.domain.exception.InvalidSentimentTextException;
import com.alura.SentimentAPI.domain.model.Sentiment;
import com.alura.SentimentAPI.domain.model.SentimentLote;
import com.alura.SentimentAPI.domain.model.SentimentResult;
import com.alura.SentimentAPI.domain.port.out.SentimentAnalysisPort;
import com.alura.SentimentAPI.domain.port.out.SentimentRepositoryPort;
import com.alura.SentimentAPI.domain.port.out.TraduccionPort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AnalizeLoteSentimentservice implements AnalizeLoteSentimentUseCase {

    private final SentimentAnalysisPort analizePort;
    private final SentimentRepositoryPort repositoryPort;
    private final TraduccionPort traduccionPort;

    public AnalizeLoteSentimentservice(SentimentAnalysisPort analizePort, SentimentRepositoryPort repositoryPort, TraduccionPort traduccionPort) {
        this.analizePort = analizePort;
        this.repositoryPort = repositoryPort;
        this.traduccionPort = traduccionPort;
    }

    @Override
    public LoteSentimentResponse analizeAll(List<SentimentLoteRequest> texto) {

//        List<SentimentResult> resultadostotales = new ArrayList<>();
        List<SentimentLote> loteGuardar = new ArrayList<>();

//        texto Ignorado que no cumple con 5 caracters
        List<String> textoIgnorado = new ArrayList<>();

//        texto sin ignorar
        List<SentimentValidos> textoOriginalValido = new ArrayList<>();

        for (SentimentLoteRequest textoOrig : texto) {
            try {

                if (textoOrig.getText().length() < 5) {
                    textoIgnorado.add(textoOrig.getText());
                } else if (textoOrig.getText().length() >= 5){
                    SentimentValidos Agregar = new SentimentValidos(textoOrig.getText(), textoOrig.getIdioma());
                    textoOriginalValido.add(Agregar);
                }
            } catch (RuntimeException e) {
                throw new InvalidSentimentTextException(
                        "Error al distribuir los comentarios que cumplen para ser procesados o no " + textoOrig + " , " + e
                );
            }

        }

        // estadisticos
        int positivos = 0;
        int negativos = 0;
        int neutros = 0;

        for (SentimentValidos text : textoOriginalValido) {

            try {
//              traduciendo el texto a ingles por que el modelo entrenado esta en ingles
                String textTraducer = traduccionPort.traducir(text.getText(), text.getIdioma());

//                analizando el comentario
                SentimentResult result = analizePort.analyze(new Sentiment(textTraducer));

                SentimentLote sentimentOriginal = new SentimentLote(text.getText(), result.getLabel(),
                        result.getProbability(), text.getIdioma());

                loteGuardar.add(sentimentOriginal);

                // Sumar al contador a segun el tipo: positivo, negativo o neutro
                switch (result.getLabel().toLowerCase()) {
                    case "positivo" -> positivos++;
                    case "negativo" -> negativos++;
                    case "neutro" -> neutros++;

                }


//              Guardando los datos por lotes de 50 en la bd
                if (loteGuardar.size() == 50) {
                    repositoryPort.saveAll(loteGuardar);
                    loteGuardar.clear();
                }

            } catch (RuntimeException e){
                throw new InvalidSentimentTextException(
                        "Error procesando el texto" + text + " , " + e);
            }
        }

        if (!loteGuardar.isEmpty()) {
            repositoryPort.saveAll(loteGuardar);
        }

//        Top 5 Mejores comentarios
        List<SentimentLote> Top5 = loteGuardar
                .stream().limit(5)
                .filter(r -> r.getPrevision().toLowerCase().contains("positive"))
                .sorted(Comparator.comparing(SentimentLote::getProbabilidad).reversed())
                .collect(Collectors.toList());

//        FILTRADO CRITICO: solo comentarios negativos con probabilidad >= 0.8
        List<SentimentLote> Criticos = loteGuardar
                .stream()
                .filter(r -> r.getPrevision().toLowerCase().contains("negative"))
                .filter(r -> r.getProbabilidad() >= 0.8)
                .collect(Collectors.toList());


        return new LoteSentimentResponse(
                positivos, negativos, neutros, loteGuardar.size(),
                textoIgnorado.size(),texto.size(),
                Top5,
                Criticos
        );
    }
}
