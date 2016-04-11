
package com.warehouse.services;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Purchase;

public interface PurchaseService {

    public void createPurchase(Purchase newModel) throws WarehouseClientException;

}
