
package com.warehouse.services.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.purchase.PurchaseDao;
import com.warehouse.entity.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public Purchase createPurchase(Purchase newPurchaseModel) throws WarehouseServerException {
        System.out.println(newPurchaseModel.getOrderDate());
        System.out.println(newPurchaseModel.getSupplier());
        System.out.println(newPurchaseModel.getDeliveryDate());
        purchaseDao.createPurchase(newPurchaseModel);
        return newPurchaseModel;
    }
}
