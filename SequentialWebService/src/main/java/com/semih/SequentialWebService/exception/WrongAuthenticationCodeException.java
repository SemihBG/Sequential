package com.semih.SequentialWebService.exception;

public class WrongAuthenticationCodeException extends RuntimeException{

    public WrongAuthenticationCodeException(){
        super("Wrong authentication code");
    }

    public WrongAuthenticationCodeException(String message) {
        super(message);
    }

}
