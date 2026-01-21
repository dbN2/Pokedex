package com.example.pokedex.exception;

public class UnknownErrorException extends RuntimeException {
    public UnknownErrorException(String error) {
        super(error);
    }
}
