package com.registro.entrenamientos.exception;

public class EmailYaExisteException extends RuntimeException {

    public EmailYaExisteException(String message){
        super(message);
    }

}
