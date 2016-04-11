
package com.warehouse.services;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;

public interface AccountService {
    public void createAccount(Account account) throws WarehouseClientException;
}
