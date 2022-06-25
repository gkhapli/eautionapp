package com.eauctionapp.common.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ErrorMessage {
    private final String message;
    private final CustomException.ErrorCode errorCode;

    public ErrorMessage(CustomException.ErrorCode errorCode, Exception ex){
        this.errorCode = errorCode;
        this.message = ex.getMessage();
        log.debug("{} with error httpcode {}",ex.getMessage(),errorCode.httpCode);
    }
}
