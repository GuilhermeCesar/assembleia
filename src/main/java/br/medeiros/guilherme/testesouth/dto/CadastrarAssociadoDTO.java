package br.medeiros.guilherme.testesouth.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;

@Value
@With
@JsonDeserialize(builder = CadastrarAssociadoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "DTO de cadastro de associado")
public class CadastrarAssociadoDTO {

    @NotNull
    String nome;
    @NotNull
    String cpf;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
