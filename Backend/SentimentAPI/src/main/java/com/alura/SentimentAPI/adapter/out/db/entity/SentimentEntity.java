package com.alura.SentimentAPI.adapter.out.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "sentiments")
@Entity(name = "SentimentEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SentimentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false)
    private String prevision;
    @Column(nullable = false)
    private Double probabilidad;
    @Column(nullable = false)
    private String idioma;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    public SentimentEntity(String text, String label, double probability, String idioma) {
        this.id = null;
        this.texto = text;
        this.prevision = label;
        this.probabilidad = probability;
        this.idioma = idioma;
    }


}
