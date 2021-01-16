package br.medeiros.guilherme.testesouth.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateDeserializer {

    private DateDeserializer() {
        super();
    }

    public static LocalDateTime localDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalTime localTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        return LocalTime.parse(date, formatter);
    }
}
