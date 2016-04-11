
package com.warehouse.dao.purchase;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Purchase;

public interface PurchaseDao {

    public void createPurchase(Purchase purchase) throws WarehouseServerException;
}
