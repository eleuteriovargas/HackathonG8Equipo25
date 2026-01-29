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

        List<SentimentLote> loteGuardar = new ArrayList<>();
        List<SentimentLote> listaProcesada = new ArrayList<>();


//        texto Ignorado que no cumple con 5 caracters
        List<String> textoIgnorado = new ArrayList<>();

//        texto sin ignorar
        List<SentimentValidos> textoOriginalValido = new ArrayList<>();


        for (SentimentLoteRequest textoOrig : texto) {

            var longitud = 5;

            try {
                if (textoOrig.getText().length() <= 4) {
                    textoIgnorado.add(textoOrig.getText());
                    System.out.println("tiene texto invalido " + textoOrig.getText());
                } else if (textoOrig.getText().length() >= longitud){
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
                listaProcesada.add(sentimentOriginal);

                if (result.getLabel().equals("Positive")){
                    positivos++;
                }else if (result.getLabel().equals("Negative")) {
                    negativos++;
                } else neutros++;



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

//        Top 5 Mejores comentarios para frontend
        List<SentimentLote> Top5 = listaProcesada
                .stream()
                .filter(r -> r.getPrevision().toLowerCase().contains("positive"))
                .sorted(Comparator.comparing(SentimentLote::getProbabilidad).reversed())
                .limit(5)
                .collect(Collectors.toList());

//        FILTRADO CRITICO: solo comentarios negativos con probabilidad >= 0.8
        List<SentimentLote> Criticos = listaProcesada
                .stream()
                .filter(r -> r.getPrevision().toLowerCase().contains("negative"))
                .filter(r -> r.getProbabilidad() >= 0.8)
                .collect(Collectors.toList());

        System.out.println("todos los neutros " +  neutros);


        return new LoteSentimentResponse(
                positivos, negativos, neutros,
                textoIgnorado.size(),
                texto.size(),
                textoOriginalValido.size(),
                Top5,
                Criticos
        );
    }
}
