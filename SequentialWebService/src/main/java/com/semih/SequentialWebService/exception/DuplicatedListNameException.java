package com.semih.SequentialWebService.exception;

public class DuplicatedListNameException extends RuntimeException{

    public DuplicatedListNameException(){
        super("Duplicated list name");
    }


    public DuplicatedListNameException(String message){
        super(message);
    }

}
