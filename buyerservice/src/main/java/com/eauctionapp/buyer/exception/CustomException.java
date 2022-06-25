package com.eauctionapp.buyer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

    public enum  ErrorCode{
        NOT_FOUND("Not found", HttpStatus.NOT_FOUND.value()),
        BAD("Bad request",HttpStatus.BAD_REQUEST.value()),
        MISSING("Some fields are missing",HttpStatus.BAD_REQUEST.value()),
        INCORRECT("Provided data is incorrect",HttpStatus.UNPROCESSABLE_ENTITY.value()),
        EXIST("Object already exists",HttpStatus.NOT_ACCEPTABLE.value()),
        SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()),
        BID_END_DATE_INPAST("The bid end date is in the past so the product cannot be deleted",HttpStatus.INTERNAL_SERVER_ERROR.value()),
        BID_END_DATE_INPAST_BID("The bid end date is in the past so the bid cannot be placed/updated",HttpStatus.INTERNAL_SERVER_ERROR.value()),
        BID_END_DATE_SHOULD_BE_IN_FUTURE("Bid end date should be a future date", HttpStatus.INTERNAL_SERVER_ERROR.value()),
        NO_BID_FOUND_FOR_PRODUCTID("No bid found for the productID",HttpStatus.INTERNAL_SERVER_ERROR.value()),
        NOPRODUCTIDPRESENT("The product is not present. Please select a valid product",HttpStatus.INTERNAL_SERVER_ERROR.value());
        public final String message;
        public final Integer httpCode;

        ErrorCode(String message,Integer httpCode){
            this.message = message;
            this.httpCode = httpCode;
        }
    }

    public final String message;
    public final ErrorCode errorCode;

    public CustomException(ErrorCode code){
        this.message = code.message;
        this.errorCode = code;
    }

    public CustomException(ErrorCode code,String message){
        this.message = message;
        this.errorCode = code;
    }
}
