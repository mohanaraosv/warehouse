package com.warehouse.ws;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Order;

public interface OrderClientWS {
	
    public Order readOrderDetails(Order item) throws WarehouseClientException;

}
