package br.medeiros.guilherme.testesouth.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@With
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = CpfValidatorDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Getter
public class ContagemVotacaoDTO {

    private Boolean aprovado;
    private Long quantidade;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}
