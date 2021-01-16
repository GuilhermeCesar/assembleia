package br.medeiros.guilherme.testesouth.dto;


import br.medeiros.guilherme.testesouth.helper.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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
public class CadastrarSessaoDTO {

    @NotNull
    String pauta;
    @NotNull
    LocalDateTime inicio;
    @ApiModelProperty(example = "10:10:10", value = "HH:mm:ss")
    LocalTime duracao = LocalTime.of(0, 1);

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
        LocalDateTime inicio;

        public JacksonBuilder inicio(String dataCompra) {
            this.inicio = DateDeserializer.localDateTime(dataCompra);
            return this;
        }
    }
}
