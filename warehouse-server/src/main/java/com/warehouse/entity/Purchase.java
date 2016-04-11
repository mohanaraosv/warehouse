
package com.warehouse.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "purchase")
@Entity
@Table(name = "orders", catalog = "warehouse")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 9172979683820369799L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "po_number", unique = true, nullable = false)
    private int poNumber;

    @Column(name = "ordered_date")
    private Date orderDate;

    @Column(name = "supplier_id")
    private String supplier;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
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
