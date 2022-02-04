package com.jingzhe.authentication.exception;

import java.io.Serial;

public class AccessTokenException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4839373799914922911L;

    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
