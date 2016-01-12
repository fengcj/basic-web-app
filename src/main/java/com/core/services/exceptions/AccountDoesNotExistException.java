package com.core.services.exceptions;

/**
 * Created by fcj on 16/1/12.
 */
public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(){

    }

    public AccountDoesNotExistException(String message){
        super(message);
    }

    public AccountDoesNotExistException(Throwable cause){
        super(cause);
    }

    public AccountDoesNotExistException(String message,Throwable cause){
        super(message,cause);
    }


}
