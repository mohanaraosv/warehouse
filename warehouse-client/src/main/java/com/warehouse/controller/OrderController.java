
package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Order;
import com.warehouse.services.OrderService;

@Controller("orderController")
public class OrderController {
    private String productName;

    private String barcode;

    private String description;

    private int quantity;

    private double weight;

    private double amount;

    @Autowired
    private OrderService service;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void itemDetails() throws WarehouseClientException {
        System.out.println(this.getProductName() + this.getDescription() + this.getQuantity() + this.getWeight() + this.getAmount());
        Order item = new Order();
        item.setProductName(this.getProductName());
        item.setDescription(this.getDescription());
        item.setQuantity(this.getQuantity());
        item.setWeight(this.getWeight());
        item.setAmount(this.getAmount());
        service.readOrderDetails(item);
    }
}
