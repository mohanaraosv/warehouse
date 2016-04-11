package com.warehouse.services.supplier;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Supplier;


public interface SupplierService {

    public Supplier createSupplier(Supplier createSupModel) throws WarehouseServerException;
}
