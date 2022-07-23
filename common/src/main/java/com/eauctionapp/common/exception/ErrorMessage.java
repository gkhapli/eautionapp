package com.eauctionapp.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Data
public class ErrorMessage {
    private final String message;
    private final CustomException.ErrorCode errorCode;
    private List<ValidationError> errors;

    public ErrorMessage(CustomException.ErrorCode errorCode, Exception ex){
        this.errorCode = errorCode;
        this.message = ex.getMessage();
        log.debug("{} with error httpcode {}",ex.getMessage(),errorCode.httpCode);
    }

    public ErrorMessage(CustomException.ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.message;
        log.debug("{} with error httpcode {}",errorCode.message,errorCode.httpCode);
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
