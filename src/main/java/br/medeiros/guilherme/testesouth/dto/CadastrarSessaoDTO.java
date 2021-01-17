package br.medeiros.guilherme.testesouth.dto;


import br.medeiros.guilherme.testesouth.helper.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@With
@JsonDeserialize(builder = CadastrarSessaoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Cadastro da sessao")
public class CadastrarSessaoDTO {

    @NotNull
    @ApiModelProperty("${model.pauta}")
    String pauta;
    @NotNull
    @ApiModelProperty("${model.inicio}")
    LocalDateTime inicio;
    @ApiModelProperty(example = "10:10:10", value = "Duração da sessao em HH:mm:ss")
    LocalTime duracao;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
        LocalDateTime inicio;
        LocalTime duracao;

        public JacksonBuilder() {
            this.duracao = LocalTime.of(0, 1);
        }

        public JacksonBuilder inicio(String inicio) {
            this.inicio = DateDeserializer.localDateTime(inicio);
            return this;
        }

        public JacksonBuilder duracao(String duracao) {
            this.duracao = DateDeserializer.localTime(duracao);
            return this;
        }
    }
}
