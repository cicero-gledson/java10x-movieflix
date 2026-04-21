package com.movieflix.service.exceptions;

public class UsernameOrPasswordInvalidException extends RuntimeException {
    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
