
package com.warehouse.ws;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Supplier;

public interface SupplierClientWS {

    public Supplier createSupplier(Supplier supplier) throws WarehouseClientException;
}
