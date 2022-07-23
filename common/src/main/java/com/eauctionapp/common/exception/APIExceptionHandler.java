package com.eauctionapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorMessage> handleCustomException(CustomException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(),ex);
        return new ResponseEntity<>(errorMessage , HttpStatus.valueOf(errorMessage.getErrorCode().httpCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected final ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorMessage errorMessage = new ErrorMessage(
                CustomException.ErrorCode.BAD_REQUEST
        );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessage.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessage , HttpStatus.valueOf(errorMessage.getErrorCode().httpCode));
    }
}
