package br.medeiros.guilherme.testesouth.entity;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voto")
@IdClass(VotoId.class)
@ToString
public class Voto {

    @Id
    @Column(name = "associado_id")
    private Long associadoId;

    @Id
    @Column(name = "sessao_id")
    private Long sessaoId;

    @Column
    private Boolean aprovado;
}
