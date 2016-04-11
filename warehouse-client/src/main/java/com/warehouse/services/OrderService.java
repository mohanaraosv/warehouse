
package com.warehouse.services;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Order;

public interface OrderService {

    public void readOrderDetails(Order item) throws WarehouseClientException;
}
