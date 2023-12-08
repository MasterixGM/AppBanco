package com.example.appbanco.excepciones;

public class NoLoginException extends Exception{
    public NoLoginException(String mensaje){
        super(mensaje);
    }
}
