
package com.warehouse.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;
import com.warehouse.services.AccountService;

@Controller("accountController")
@ManagedBean
@ViewScoped
public class AccountController implements Serializable {
    
    private static final long serialVersionUID = -2485334705570450432L;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private String city;

    private String country;

    private final AccountService accountService;
    
    @Autowired
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public void createAccount() throws WarehouseClientException {
        System.out.println(this.getUserName() + this.getPassword() + this.getFirstName() + this.getLastName() + this.getEmail() + this.getContactNumber() + this.getCity()
                + this.getCountry());
        Account newModel = new Account();
        newModel.setUserName(this.getUserName());
        newModel.setPassword(this.getPassword());
        newModel.setFirstName(this.getFirstName());
        newModel.setLastName(this.getLastName());
        newModel.setEmail(this.getEmail());
        newModel.setContactNumber(this.getContactNumber());
        newModel.setCity(this.getCity());
        newModel.setCountry(this.getCountry());
        accountService.createAccount(newModel);
    }

}
