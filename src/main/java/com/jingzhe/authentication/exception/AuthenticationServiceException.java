package com.jingzhe.authentication.exception;

import java.io.Serial;

public class AuthenticationServiceException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 3821091026000361075L;

    public AuthenticationServiceException(String message) {
        super(message);
    }

    public AuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
