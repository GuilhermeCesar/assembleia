package br.medeiros.guilherme.testesouth.dto;

import br.medeiros.guilherme.testesouth.helper.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Value
@With
@JsonDeserialize(builder = ErrorMessage.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@ApiModel(description = "Mensagem de erro padrão do sistema")
public class ErrorMessage {

    @Builder.Default
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime timestamp = now();
    String error;
    String message;
    String path;
    List<String> errorsList;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
