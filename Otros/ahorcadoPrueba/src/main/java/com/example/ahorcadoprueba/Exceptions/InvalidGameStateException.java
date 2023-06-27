package com.example.ahorcadoprueba.Exceptions;

public class InvalidGameStateException extends RuntimeException {
    public InvalidGameStateException(String message) {
        super(message);
    }
}