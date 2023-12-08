package com.example.appbanco.excepciones;

public class NoRegistroException extends Exception{
    public NoRegistroException(String mensaje){
        super(mensaje);
    }
}
