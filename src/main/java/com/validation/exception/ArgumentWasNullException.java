package com.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArgumentWasNullException extends NullPointerException {
    public ArgumentWasNullException(String s) {
        super(s);
    }
}
