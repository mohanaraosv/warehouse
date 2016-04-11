
package com.warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Order;
import com.warehouse.ws.OrderClientWS;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClientWS orderClientWS;

    @Override
    public void readOrderDetails(Order item) throws WarehouseClientException {
        System.out.println(item.getProductName() + item.getBarcode() + item.getDescription() + item.getQuantity() + item.getWeight() + item.getAmount());
        orderClientWS.readOrderDetails(item);
    }
}
