package br.com.allfood.restaurantesapi.Services.ServiceException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleException(ServiceException e) {
        // log exception
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

}
