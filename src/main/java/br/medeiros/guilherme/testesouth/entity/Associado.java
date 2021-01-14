package br.medeiros.guilherme.testesouth.entity;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "associado")
public class Associado {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String cpf;
}
