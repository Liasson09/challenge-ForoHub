package com.milagros.challenge_ForoHub.infra.exceptions;

public class TopicoNotFoundException extends RuntimeException{
    public TopicoNotFoundException(String message) {
        super(message);
    }
}
