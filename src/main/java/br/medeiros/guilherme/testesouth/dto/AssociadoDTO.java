package br.medeiros.guilherme.testesouth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = AssociadoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AssociadoDTO {

    Long id;
    String nome;
    String cpf;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
