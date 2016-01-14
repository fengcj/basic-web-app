package com.core.services.exceptions;

/**
 * Created by fengc on 1/13/2016.
 */
public class AccountExistsException extends RuntimeException {

    public AccountExistsException(){

    }

    public AccountExistsException(Throwable cause){
        super(cause);
    }

    public AccountExistsException(String message){
        super(message);
    }

    public AccountExistsException(String message,Throwable cause){
        super(message,cause);
    }
}
