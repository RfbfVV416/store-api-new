package org.store_api_new.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyRegisteredException extends ResponseStatusException {
    public UserAlreadyRegisteredException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
