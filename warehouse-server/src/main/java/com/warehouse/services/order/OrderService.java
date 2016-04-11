
package com.warehouse.services.order;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.OrderDetails;

public interface OrderService {

    public OrderDetails getOrderDetails(OrderDetails itemVO) throws WarehouseServerException;

}
