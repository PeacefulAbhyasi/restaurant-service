1) Create Order :

Request :

curl -L -X POST 'http://localhost:8081/order-service/order/create' -H 'Content-Type: application/json' -d '{
    "itemId" : "123456",
    "numberOfItems" : "5"
}'

Response :

{
    "resultCodeId": "CODE_0001",
    "resultCode": "ACCEPTED",
    "resultCodeMsg": "Order Accepted Successfully",
    "orderId": "ORDER_ID_1",
    "status": "ACCEPTED"
}

2) Get Order Status :

Request :

curl -L -X GET 'http://localhost:8081/order-service/order/status/ORDER_ID_1'

Response :

i) after assigning delivery person

{
    "resultCodeId": "CODE_0000",
    "resultCode": "SUCCESS",
    "resultCodeMsg": "Successful",
    "status": "PICKED"
}

ii) after delivered by person

{
    "resultCodeId": "CODE_0000",
    "resultCode": "SUCCESS",
    "resultCodeMsg": "Successful",
    "status": "DELIVERED"
}


3) Get Person status

Request :

http://localhost:8082/delivery-service/delivery/status/PERSON_ID_1

Response :

when person is active with order and on the way to deliver
(remaining time in mins, can be converted in hours accordingly)
{
    "resultCodeId": "CODE_0000",
    "resultCode": "SUCCESS",
    "resultCodeMsg": "Successful",
    "status": "ACTIVE",
    "orderId": "ORDER_ID_1",
    "orderStatus": "PICKED",
    "remainingTime": "9"
}

when person has delivered the order and now available for next order to pick

{
    "resultCodeId": "CODE_0000",
    "resultCode": "SUCCESS",
    "resultCodeMsg": "Successful",
    "status": "AVAILABLE",
    "orderId": null,
    "orderStatus": null,
    "remainingTime": null
}


4) Update Order Status By Delivery Person

Request :

curl -L -X POST 'http://localhost:8082/delivery-service/delivery/update/order' -H 'Content-Type: application/json' -d '{
    "orderId" : "ORDER_ID_1",
    "personId" : "PERSON_ID_1",
    "orderStatus" : "DELIVERED"
}'


Response :

{
    "resultCodeId": "CODE_0000",
    "resultCode": "SUCCESS",
    "resultCodeMsg": "Successful"
}
