
package com.warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Purchase;
import com.warehouse.ws.PurchaseClientWS;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseClientWS purchaseClientWS;

    @Override
    public void createPurchase(Purchase newModel) throws WarehouseClientException {
        System.out.println(newModel.getOrderDate() + newModel.getSupplier() + newModel.getDeliveryDate());
        purchaseClientWS.createPurchase(newModel);
    }
}
