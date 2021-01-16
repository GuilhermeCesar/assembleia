package br.medeiros.guilherme.testesouth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotoException extends ResponseStatusException {

    public VotoException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
