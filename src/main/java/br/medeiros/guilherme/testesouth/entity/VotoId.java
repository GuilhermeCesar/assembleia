package br.medeiros.guilherme.testesouth.entity;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoId implements Serializable {

    private Long associadoId;
    private Long sessaoId;
}
