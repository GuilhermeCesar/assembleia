package br.medeiros.guilherme.testesouth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = VotoFinalizadoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class VotoFinalizadoDTO {

    String pauta;
    Long contagemSim;
    Long contagemNao;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
