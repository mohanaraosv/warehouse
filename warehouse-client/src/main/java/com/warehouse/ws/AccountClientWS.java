
package com.warehouse.ws;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;

public interface AccountClientWS {

    public Account createAccount(Account newModel) throws WarehouseClientException;
}
