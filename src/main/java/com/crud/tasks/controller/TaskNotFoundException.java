package com.crud.tasks.controller;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Wrong id");
    }
    public TaskNotFoundException(final String message) {
        super(message);
    }
}

