package br.medeiros.guilherme.testesouth.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.temporal.ChronoField.NANO_OF_DAY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "pauta")
    private String pauta;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "duracao")
    private LocalTime duracao;

    public LocalDateTime getFinalSessao() {
        return dataInicio.plus(duracao.getLong(NANO_OF_DAY), NANO_OF_DAY.getBaseUnit());
    }
}
