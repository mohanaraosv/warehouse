
package com.warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;
import com.warehouse.ws.AccountClientWS;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountClientWS accountClientWS;

    @Override
    public void createAccount(Account newModel) throws WarehouseClientException {
        System.out.println(newModel.getUserName() + newModel.getPassword() + newModel.getFirstName() + newModel.getLastName() + newModel.getEmail() + newModel.getContactNumber()
                + newModel.getCity() + newModel.getCountry());
        accountClientWS.createAccount(newModel);
    }
}
