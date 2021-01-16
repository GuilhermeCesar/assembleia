package br.medeiros.guilherme.testesouth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@With
@JsonDeserialize(builder = VotoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class VotoDTO {

    @NotNull
    @Min(0)
    Long idAssociado;
    @NotNull
    Boolean voto;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
