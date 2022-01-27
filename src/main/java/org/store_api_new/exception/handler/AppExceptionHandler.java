package org.store_api_new.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class AppExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> apiExceptionHandling(ResponseStatusException exception, WebRequest request){
        log.error("Some api error happened", exception);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), exception.getStatus().value());
        return new ResponseEntity<>(errorDetails,exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        log.error("Some error happened", exception);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

