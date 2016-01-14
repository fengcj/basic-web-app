package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fengc on 1/13/2016.
 */


@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException() {
    }
    public ConflictException(Throwable cause) {
        super(cause);
    }
}
