package com.crud.tasks.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Wrong user id");
    }
    public UserNotFoundException(final String message) {
        super(message);
    }
}

