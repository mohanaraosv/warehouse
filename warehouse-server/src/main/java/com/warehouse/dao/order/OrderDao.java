
package com.warehouse.dao.order;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.OrderDetails;

public interface OrderDao {

    public void saveOrder(OrderDetails item) throws WarehouseServerException;

}
