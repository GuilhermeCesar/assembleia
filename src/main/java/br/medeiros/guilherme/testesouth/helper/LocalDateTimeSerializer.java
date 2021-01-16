package br.medeiros.guilherme.testesouth.helper;

import br.medeiros.guilherme.testesouth.exception.SerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@NoArgsConstructor
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public void serialize(LocalDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            jsonGenerator.writeString(formatter.format(zonedDateTime));
        } catch (Exception ex) {
            log.error("Falha ao serializar a data", ex);
            throw new SerializerException(HttpStatus.BAD_REQUEST, "Falha ao serializar a data");
        }

    }
}
