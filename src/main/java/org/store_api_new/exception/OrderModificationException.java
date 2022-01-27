package org.store_api_new.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderModificationException extends ResponseStatusException {
    public OrderModificationException(HttpStatus httpStatus, String reason) {
        super(httpStatus, reason);
    }
}
