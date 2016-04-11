package com.warehouse.ws;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Purchase;

public interface PurchaseClientWS {

	public Purchase createPurchase(Purchase newModel) throws WarehouseClientException;
}
