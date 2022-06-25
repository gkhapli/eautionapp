package com.eauctionapp.seller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorMessage> exception(CustomException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(),ex);
        return new ResponseEntity<>(errorMessage , HttpStatus.valueOf(errorMessage.getErrorCode().httpCode));
    }
}
