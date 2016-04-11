package com.warehouse.services;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Supplier;

public interface SupplierService {
	 public void createSupplier(Supplier createSupModel) throws WarehouseClientException;
}
