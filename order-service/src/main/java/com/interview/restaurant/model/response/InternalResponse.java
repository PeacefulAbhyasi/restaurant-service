package com.interview.restaurant.model.response;

import com.interview.restaurant.model.common.MerchantInternalResponse;

import java.io.Serializable;

public class InternalResponse implements Serializable {

    private String resultCodeId;
    private String resultCode;
    private String resultCodeMsg;

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
