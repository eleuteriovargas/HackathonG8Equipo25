package com.alura.SentimentAPI.domain.comentarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "comentarios")
@Entity(name = "Comentario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {
    private Long id;
    private String texto;
    private String prevision;
    private Double probabilidad;
    @CreationTimestamp
    private LocalDateTime fecha;

    public Comentario(Long id, DatosComentarioRequest datos, String prevision, Double probabilidad) {
        this.id = null;
        this.texto = datos.texto();
        this.prevision = prevision;
        this.probabilidad = 5.3;
    }
}
