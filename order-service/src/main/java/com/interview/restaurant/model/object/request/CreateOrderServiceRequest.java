package com.interview.restaurant.model.object.request;


public class CreateOrderServiceRequest extends InternalServiceRequest {

    private String itemId;
    private String numberOfItems;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(String numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
