package com.scholarly.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;


public class NotAllowedException extends RuntimeException {

    public NotAllowedException(String message) {
        super(message);
    }
}
