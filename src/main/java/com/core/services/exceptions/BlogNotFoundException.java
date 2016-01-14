package com.core.services.exceptions;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogNotFoundException extends RuntimeException{

    public BlogNotFoundException() {
    }
    public BlogNotFoundException(Throwable cause) {
        super(cause);
    }
    public BlogNotFoundException(String message) {
        super(message);
    }
    public BlogNotFoundException(String message,Throwable cause){
        super(message,cause);
    }


}
