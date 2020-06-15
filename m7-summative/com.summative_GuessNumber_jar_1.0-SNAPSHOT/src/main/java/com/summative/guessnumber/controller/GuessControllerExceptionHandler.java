/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author ashleytulloch
 */
@ControllerAdvice
@RestController
public class GuessControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String INVALID_GUESS_MESSAGE = "Could not use guess input. "
                                            + "Please insert a four digit guess and try again.";
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handlesqlException(
                            SQLIntegrityConstraintViolationException ex,
                            WebRequest request) {
        Error err = new Error();
        err.setMessage(INVALID_GUESS_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
