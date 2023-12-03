package com.example.appbanco.excepciones;

public class NoDatosException extends Exception {
    public NoDatosException(String mensaje) {
        super(mensaje);
    }
}