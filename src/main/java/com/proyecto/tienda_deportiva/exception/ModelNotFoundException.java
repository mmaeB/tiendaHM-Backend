package com.proyecto.tienda_deportiva.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
    // Se desencadena en tiempo de ejecución

    public ModelNotFoundException(String message) {
        super(message);
    }
}
