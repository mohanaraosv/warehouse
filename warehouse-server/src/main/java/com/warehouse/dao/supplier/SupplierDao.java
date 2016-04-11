
package com.warehouse.dao.supplier;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Supplier;

public interface SupplierDao {

    public void createSupplier(Supplier createModel) throws WarehouseServerException;
}
