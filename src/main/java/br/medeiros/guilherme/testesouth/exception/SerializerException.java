package br.medeiros.guilherme.testesouth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SerializerException extends ResponseStatusException {

    public SerializerException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
