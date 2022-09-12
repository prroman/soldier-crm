package com.rpr.soldierscrm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SoldierNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public SoldierNotFoundException(String message) {
        super(message);
    }

    public SoldierNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
