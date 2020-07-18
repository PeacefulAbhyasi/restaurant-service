package com.interview.delivery.enums;

public enum ResultCode {

    SUCCESS("CODE_0000", "SUCCESS", "Successful"),
    ACCEPTED("CODE_0001", "ACCEPTED", "Order Accepted Successfully"),
    FAILED("CODE_0002", "FAILED", "Order Attempt Failed"),
    INVALID_PARAM("CODE_0003", "FAILED", "Invalid Parameter"),
    IDEMPOTENT_ERROR("CODE_0004", "FAILED", "Order Already Exists"),
    NOT_FOUND("CODE_0005", "FAILED", "No Result Found"),
    DELIVERY_PERSON_NOT_AVAILABLE("CODE_0006", "FAILED", "Delivery person is not available"),
    INVALID_REQUEST("CODE_0007", "FAILED", "Invalid Request"),;

    private String resultCodeId;
    private String resultCode;
    private String resultCodeMsg;

    ResultCode(String resultCodeId, String resultCode, String resultCodeMsg) {
        this.resultCodeId = resultCodeId;
        this.resultCode = resultCode;
        this.resultCodeMsg = resultCodeMsg;
    }

    public String getResultCodeId() {
        return resultCodeId;
    }

    public void setResultCodeId(String resultCodeId) {
        this.resultCodeId = resultCodeId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCodeMsg() {
        return resultCodeMsg;
    }

    public void setResultCodeMsg(String resultCodeMsg) {
        this.resultCodeMsg = resultCodeMsg;
    }
}
