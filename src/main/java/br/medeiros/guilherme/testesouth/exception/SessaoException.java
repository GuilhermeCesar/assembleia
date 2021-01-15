package br.medeiros.guilherme.testesouth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SessaoException extends ResponseStatusException {

    public SessaoException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public SessaoException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
