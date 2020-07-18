package com.interview.delivery.model.common;

import com.interview.delivery.constants.Constants;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeliveryPersonDetails implements Serializable {

    private String personId;
    private DeliveryPersonStatusEnum status;

    public DeliveryPersonDetails(String personId) {
        this.personId = personId;
    }

    public String getKey() {
        return new StringBuilder(Constants.DELIVERY_PERSON_DETAIL_PREFIX).append(personId).toString();
    }

}
