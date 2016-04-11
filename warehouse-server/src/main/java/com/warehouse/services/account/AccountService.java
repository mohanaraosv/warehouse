
package com.warehouse.services.account;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Account;

public interface AccountService {

    public Account createAccount(Account tryModel) throws WarehouseServerException;

}
