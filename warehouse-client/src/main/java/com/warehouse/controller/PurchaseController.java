
package com.warehouse.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Purchase;
import com.warehouse.services.PurchaseService;

@Controller("purchaseController")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    private Date orderDate;

    private String supplier;

    private Date deliveryDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void createPurchase() throws WarehouseClientException {
        System.out.println(this.getOrderDate() + this.getSupplier() + this.getDeliveryDate());
        Purchase newModel = new Purchase();
        newModel.setSupplier(this.getSupplier());
        newModel.setDeliveryDate(this.getDeliveryDate());
        service.createPurchase(newModel);
    }
}
