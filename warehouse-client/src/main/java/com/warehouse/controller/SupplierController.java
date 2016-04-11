
package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Supplier;
import com.warehouse.services.SupplierService;

@Controller("supplierController")
public class SupplierController {

    private String supplierId;

    private String supplierName;

    private String address;

    private String pincode;

    private String city;

    private String country;

    private String email;

    private String phone;

    private String companyName;

    @Autowired
    private SupplierService service;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void createSupplier() throws WarehouseClientException {
        System.out.println(this.getSupplierId() + this.getSupplierName() + this.getAddress() + this.getPincode() + this.getCity() + this.getCountry() + this.getEmail()
                + this.getPhone() + this.getCompanyName());
        Supplier createSupModel = new Supplier();
        createSupModel.setSupplierId(this.getSupplierId());
        createSupModel.setSupplierName(this.getSupplierName());
        createSupModel.setAddress(this.getAddress());
        createSupModel.setPincode(this.getPincode());
        createSupModel.setCity(this.getCity());
        createSupModel.setCountry(this.getCountry());
        createSupModel.setEmail(this.getEmail());
        createSupModel.setPhone(this.getPhone());
        createSupModel.setCompanyName(this.getCompanyName());
        service.createSupplier(createSupModel);
    }
}