package org.store_api_new.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundEntityException extends ResponseStatusException {
    public NotFoundEntityException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
