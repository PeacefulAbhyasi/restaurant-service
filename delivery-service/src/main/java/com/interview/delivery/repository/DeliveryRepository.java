package com.interview.delivery.repository;

import com.interview.delivery.config.DeliveryConfig;
import com.interview.delivery.constants.Constants;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.model.common.DeliveryOrderDetails;
import com.interview.delivery.model.common.DeliveryPersonDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class DeliveryRepository {

    @Autowired
    private DeliveryConfig config;

    private static ConcurrentHashMap<String, DeliveryPersonDetails> deliveryPersonDetailsMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, DeliveryOrderDetails> deliveryOrderDetailsMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, DeliveryOrderDetails> deliveryOrderDetailsByOrderIdMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, DeliveryOrderDetails> deliveryOrderDetailsByPersonIdMap = new ConcurrentHashMap<>();

    private ReentrantLock lock = new ReentrantLock();

    public void initialize() {
        int personCount = Integer.parseInt(config.getDeliveryPersons());
        for(int i=1;i<=personCount;i++) {
            String key = new StringBuilder(Constants.PERSON_ID_PREFIX).append(i).toString();
            deliveryPersonDetailsMap.put(key, new DeliveryPersonDetails(key, DeliveryPersonStatusEnum.AVAILABLE));
        }
    }

    public void save(DeliveryPersonDetails deliveryPersonDetails) {
        deliveryPersonDetailsMap.put(deliveryPersonDetails.getKey(), deliveryPersonDetails);
    }

    public void update(DeliveryPersonDetails deliveryPersonDetails) {
        try {
            lock.lock();
            deliveryPersonDetailsMap.put(deliveryPersonDetails.getKey(), deliveryPersonDetails);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        } finally {
            lock.unlock();
        }
    }

    public void save(DeliveryOrderDetails deliveryOrderDetails) {
        deliveryOrderDetailsMap.put(deliveryOrderDetails.getKey(), deliveryOrderDetails);
        deliveryOrderDetailsByOrderIdMap.put(deliveryOrderDetails.getOrderId(), deliveryOrderDetails);
        deliveryOrderDetailsByPersonIdMap.put(deliveryOrderDetails.getDeliveryPersonId(), deliveryOrderDetails);
    }

    public void removeOrderDetailByPersonId(String personId) {
        deliveryOrderDetailsByPersonIdMap.remove(personId);
    }

    public DeliveryOrderDetails getOrderDetails(String key) {
        return deliveryOrderDetailsMap.get(key);
    }

    public DeliveryOrderDetails getOrderDetailsByOrderId(String orderId) {
        return deliveryOrderDetailsByOrderIdMap.get(orderId);
    }

    public DeliveryOrderDetails getOrderDetailsByPersonId(String personId) {
        return deliveryOrderDetailsByPersonIdMap.get(personId);
    }

    public DeliveryPersonDetails getPersonDetails(String key) {
        return deliveryPersonDetailsMap.get(key);
    }

    public List<DeliveryPersonDetails> getAllPersonDetails(DeliveryPersonStatusEnum status) {
        List<DeliveryPersonDetails> list = new LinkedList<>();
        for(Map.Entry<String, DeliveryPersonDetails> entry : deliveryPersonDetailsMap.entrySet()) {
            if(status.getStatus().equals(entry.getValue().getStatus().getStatus())) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public List<DeliveryPersonDetails> getAllPersonDetails() {
        List<DeliveryPersonDetails> list = new LinkedList<>();
        for(Map.Entry<String, DeliveryPersonDetails> entry : deliveryPersonDetailsMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

}
