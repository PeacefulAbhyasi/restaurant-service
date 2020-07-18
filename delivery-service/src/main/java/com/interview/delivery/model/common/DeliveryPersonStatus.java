package com.interview.delivery.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeliveryPersonStatus implements Serializable {

    private String personId;
    private String status;
}
