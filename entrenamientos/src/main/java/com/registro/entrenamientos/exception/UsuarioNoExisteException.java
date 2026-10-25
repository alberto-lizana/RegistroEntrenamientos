package com.registro.entrenamientos.exception;

public class UsuarioNoExisteException extends RuntimeException {

    public UsuarioNoExisteException(String message){
        super(message);
    }

}
