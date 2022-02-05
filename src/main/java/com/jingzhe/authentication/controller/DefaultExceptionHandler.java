package com.jingzhe.authentication.controller;

import com.jingzhe.authentication.api.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({
            ServerWebInputException.class
    })
    private ResponseEntity<Object> handleInvalidDataException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        log.info("Bad request:{}", apiError.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleUnhandledException(Exception ex) {
        log.warn("Unhandled exception:", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
