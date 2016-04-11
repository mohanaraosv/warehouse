
package com.warehouse.ws.account;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Account;
import com.warehouse.services.account.AccountService;

@Service("accountServiceWS")

public class AccountServiceWSImpl implements AccountServiceWS {

    @Autowired
    private AccountService accountService;

    @Override
    public Response createAccount(Account tryModel) throws WarehouseServerException {
        System.out.println(tryModel.getUserName() + tryModel.getPassword() + tryModel.getFirstName() + tryModel.getLastName() + tryModel.getEmail() + tryModel.getContactNumber()
                + tryModel.getCity() + tryModel.getCountry() + "33333333333");
        accountService.createAccount(tryModel);
        return null;
    }

}
