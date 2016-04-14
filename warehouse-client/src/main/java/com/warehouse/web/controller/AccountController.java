
package com.warehouse.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;
import com.warehouse.services.AccountService;
import com.warehouse.web.view.AccountView;

@Controller("accountController")
@RequestScoped
public class AccountController implements Serializable {

    private static final long serialVersionUID = -2485334705570450432L;

    private AccountView accountView;

    @Autowired
    private AccountService accountService;

    public AccountView getAccountView() {
        return accountView;
    }

    public void setAccountView(AccountView accountView) {
        this.accountView = accountView;
    }

    public void createAccount() throws WarehouseClientException {
        Account newAccount = new Account();
        newAccount.setUserName(accountView.getUserName());
        newAccount.setPassword(accountView.getPassword());
        newAccount.setFirstName(accountView.getFirstName());
        newAccount.setLastName(accountView.getLastName());
        newAccount.setEmail(accountView.getEmail());
        newAccount.setContactNumber(accountView.getContactNumber());
        newAccount.setCity(accountView.getCity());
        newAccount.setCountry(accountView.getCountry());
        accountService.createAccount(newAccount);
    }

    @PostConstruct
    public void init() {
        accountView = new AccountView();
    }
}
