package com.interview.restaurant.exception;

import com.interview.restaurant.enums.ResultCode;
import lombok.Data;

@Data
public class RequestValidationException extends RuntimeException {

    private ResultCode resultCode;

    private String errorCode;
    private String errorMsg;

    public RequestValidationException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RequestValidationException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public RequestValidationException(ResultCode resultCode) {
        super(resultCode.getResultCodeMsg());
        this.resultCode = resultCode;
    }

    public RequestValidationException(Throwable cause, ResultCode resultCode) {
        super(resultCode.getResultCodeMsg(), cause);
        this.resultCode = resultCode;
    }

}

