
package com.warehouse.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.account.AccountDao;
import com.warehouse.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao createAccountDAO;

    @Override
    public Account createAccount(Account tryModel) throws WarehouseServerException {
        System.out.println(tryModel.getUserName() + tryModel.getPassword() + tryModel.getFirstName() + tryModel.getLastName() + tryModel.getEmail() + tryModel.getContactNumber()
                + tryModel.getCity() + tryModel.getCountry());
        createAccountDAO.createAccount(tryModel);
        return tryModel;
    }

}
