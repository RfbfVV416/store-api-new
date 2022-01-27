package org.store_api_new.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductOutOfStockException extends ResponseStatusException {
    public ProductOutOfStockException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
