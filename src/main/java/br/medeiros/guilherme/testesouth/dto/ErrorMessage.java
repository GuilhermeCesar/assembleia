package br.medeiros.guilherme.testesouth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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
public class ErrorMessage {

    @Builder.Default
    LocalDateTime timestamp = now();
    String error;
    String message;
    String path;
    List<String> errorsList;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
