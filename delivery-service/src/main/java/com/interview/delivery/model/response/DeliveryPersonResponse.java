package com.interview.delivery.model.response;

import com.interview.delivery.model.common.DeliveryPersonStatus;
import lombok.Data;

import java.util.List;

@Data
public class DeliveryPersonResponse extends InternalResponse {

    private List<DeliveryPersonStatus> status;
}
