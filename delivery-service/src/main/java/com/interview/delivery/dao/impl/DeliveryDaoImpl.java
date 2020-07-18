package com.interview.delivery.dao.impl;

import com.interview.delivery.dao.DeliveryDao;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.model.common.DeliveryOrderDetails;
import com.interview.delivery.model.common.DeliveryPersonDetails;
import com.interview.delivery.model.common.DeliveryPersonStatus;
import com.interview.delivery.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DeliveryDaoImpl implements DeliveryDao {

    @Autowired
    private DeliveryRepository repository;

    @Override
    public DeliveryOrderDetails getOrderDetails(String key) {
        DeliveryOrderDetails orderDetails = null;
        try {
            orderDetails = repository.getOrderDetails(key);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return orderDetails;
    }

    @Override
    public DeliveryOrderDetails getOrderDetailsByOrderId(String orderId) {
        DeliveryOrderDetails orderDetails = null;
        try {
            orderDetails = repository.getOrderDetailsByOrderId(orderId);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return orderDetails;
    }

    @Override
    public DeliveryOrderDetails getOrderDetailsByPersonId(String personId) {
        DeliveryOrderDetails orderDetails = null;
        try {
            orderDetails = repository.getOrderDetailsByPersonId(personId);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return orderDetails;
    }

    @Override
    public void removeOrderDetailByPersonId(String personId) {
        try {
            repository.removeOrderDetailByPersonId(personId);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }

    @Override
    public boolean createOrder(DeliveryOrderDetails deliveryOrderDetails) {
        try {
            repository.save(deliveryOrderDetails);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }

    @Override
    public boolean updateOrder(DeliveryOrderDetails orderDetails) {
        try {
            repository.save(orderDetails);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }

    @Override
    public boolean updatePersonDetailStatus(DeliveryPersonDetails personDetails) {
        try {
            repository.update(personDetails);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }

    @Override
    public DeliveryPersonDetails getPersonDetails(String personId) {
        DeliveryPersonDetails personDetails = null;
        try {
            personDetails = repository.getPersonDetails(personId);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return personDetails;
    }

    @Override
    public List<DeliveryPersonStatus> getPersonDetails() {
        List<DeliveryPersonStatus> list = null;
        try {
            List<DeliveryPersonDetails> personStatusList = repository.getAllPersonDetails();
            list = personStatusList.stream().map(personDetails -> {
              return new  DeliveryPersonStatus(personDetails.getPersonId(), personDetails.getStatus().getStatus());
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return list;
    }

    @Override
    public List<DeliveryPersonStatus> getPersonDetails(DeliveryPersonStatusEnum personStatusEnum) {
        List<DeliveryPersonStatus> list = null;
        try {
            List<DeliveryPersonDetails> personStatusList = repository.getAllPersonDetails(personStatusEnum);
            list = personStatusList.stream().map(personDetails -> {
                return new  DeliveryPersonStatus(personDetails.getPersonId(), personDetails.getStatus().getStatus());
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return list;
    }

    @Override
    public List<DeliveryPersonDetails> getDeliveryPersonDetails(DeliveryPersonStatusEnum personStatusEnum) {
        List<DeliveryPersonDetails> list = null;
        try {
            return repository.getAllPersonDetails(personStatusEnum);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return list;
    }

}
