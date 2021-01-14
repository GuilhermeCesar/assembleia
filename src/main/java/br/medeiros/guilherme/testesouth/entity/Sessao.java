package br.medeiros.guilherme.testesouth.entity;

import lombok.*;

import javax.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

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

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "duracao")
    private Duration duracao;
}
