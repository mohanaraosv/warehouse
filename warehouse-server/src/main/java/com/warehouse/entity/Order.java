
package com.warehouse.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@Entity
@Table(name = "orders", catalog = "warehouse")
public class Order implements Serializable {

    private static final long serialVersionUID = 2926416698305811383L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "po_number", unique = true, nullable = false)
    private int poNumber;

    @Column(name = "ordered_date")
    private Date orderedDate;

    @Column(name = "delivery_date")
    private Date deliveredDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = { CascadeType.ALL })
    private OrderDetails orderDetails;

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
