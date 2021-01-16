package br.medeiros.guilherme.testesouth.helper;

import br.medeiros.guilherme.testesouth.exception.SerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@NoArgsConstructor
public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeString(DateTimeFormatter.ISO_LOCAL_TIME.format(value));
        } catch (Exception ex) {
            log.error("Falha ao serializar a data", ex);
            throw new SerializerException(HttpStatus.BAD_REQUEST, "Falha ao serializar a data");
        }
    }
}
