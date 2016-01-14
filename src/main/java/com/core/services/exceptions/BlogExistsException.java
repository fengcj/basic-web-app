package com.core.services.exceptions;

/**
 * Created by fengc on 1/13/2016.
 */
public class BlogExistsException extends RuntimeException{
    public BlogExistsException() {
    }
    public BlogExistsException(Throwable cause) {
        super(cause);
    }
    public BlogExistsException(String message) {
        super(message);
    }
    public BlogExistsException(String message,Throwable cause) {
        super(message,cause);
    }
}
