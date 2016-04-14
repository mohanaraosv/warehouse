
package com.warehouse.web.view;

import java.io.Serializable;
import java.util.Date;

public class PurchaseView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date orderDate;

    private String supplier;

    private Date deliveryDate;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

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
}
