package com.interview.delivery.helper;

import com.interview.delivery.dao.DeliveryDao;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.model.common.DeliveryPersonStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDeliveryService {

    private DeliveryDao deliveryDao;

    public void allocateDeliverPerson() {
        List<DeliveryPersonStatus> list = deliveryDao.getPersonDetails(DeliveryPersonStatusEnum.AVAILABLE);

    }
}
