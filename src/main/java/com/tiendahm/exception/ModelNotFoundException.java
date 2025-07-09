package com.tiendahm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
    // Se desencadena en tiempo de ejecución

    public ModelNotFoundException(String message) {
        super(message);
    }
}
