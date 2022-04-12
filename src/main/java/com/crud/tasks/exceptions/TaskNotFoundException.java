package com.crud.tasks.exceptions;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Wrong task id");
    }
    public TaskNotFoundException(final String message) {
        super(message);
    }
}

