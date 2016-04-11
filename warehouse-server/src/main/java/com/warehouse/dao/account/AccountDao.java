
package com.warehouse.dao.account;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Account;

public interface AccountDao {

    public void createAccount(Account model) throws WarehouseServerException;
}
