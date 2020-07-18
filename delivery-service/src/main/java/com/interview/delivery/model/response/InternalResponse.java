package com.interview.delivery.model.response;


import com.interview.delivery.model.common.MerchantInternalResponse;
import lombok.Data;

@Data
public class InternalResponse implements MerchantInternalResponse {

    private String resultCodeId;
    private String resultCode;
    private String resultCodeMsg;

}
