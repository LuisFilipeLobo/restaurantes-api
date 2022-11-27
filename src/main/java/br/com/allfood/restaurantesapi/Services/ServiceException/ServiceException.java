package br.com.allfood.restaurantesapi.Services.ServiceException;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException{

    private final HttpStatus httpStatus;

    public ServiceException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
