package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by fcj on 16/1/12.
 */


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(){

    }

    public BadRequestException(Throwable cause){
        super(cause);
    }

}
