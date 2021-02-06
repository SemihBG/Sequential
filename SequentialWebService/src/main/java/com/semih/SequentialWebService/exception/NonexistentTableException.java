package com.semih.SequentialWebService.exception;

public class NonexistentTableException extends RuntimeException{

    public NonexistentTableException() {
        super("No such table exits");
    }

    public NonexistentTableException(String message) {
        super(message);
    }

}
