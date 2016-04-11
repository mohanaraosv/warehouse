
package com.warehouse.services.purchase;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Purchase;

public interface PurchaseService {

    public Purchase createPurchase(Purchase purchaseModel) throws WarehouseServerException;
}
