
package com.warehouse.ws.order;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.OrderDetails;
import com.warehouse.services.order.OrderService;

@Service("orderServiceWS")
public class OrderServiceWSImpl implements OrderServiceWS {

    @Autowired
    private OrderService orderService;

    @Override
    public Response readOrderDetails(OrderDetails orderDetails) throws WarehouseServerException {
        System.out.println(orderDetails.getProductName() + orderDetails.getBarcode() + orderDetails.getDescription() + orderDetails.getQuantity() + orderDetails.getWeight()
                + orderDetails.getAmount());
        orderService.getOrderDetails(orderDetails);
        return null;
    }

}
