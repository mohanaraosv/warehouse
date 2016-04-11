
package com.warehouse.ws.purchase;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Purchase;
import com.warehouse.services.purchase.PurchaseService;

@Service("purchaseServiceWS")
public class PurchaseServiceWSImpl implements PurchaseServiceWS {

    @Autowired
    private PurchaseService purchaseService;

    @Override
    public Response createPurchase(Purchase purchaseModel) throws WarehouseServerException {
        System.out.println(purchaseModel.getDeliveryDate() + purchaseModel.getSupplier() + purchaseModel.getDeliveryDate());
        purchaseService.createPurchase(purchaseModel);
        return null;
    }
}
