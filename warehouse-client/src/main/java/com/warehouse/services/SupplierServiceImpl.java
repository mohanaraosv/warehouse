
package com.warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Supplier;
import com.warehouse.ws.SupplierClientWS;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierClientWS supplierClientWS;

    public void createSupplier(Supplier createSupModel) throws WarehouseClientException {
        System.out.println(createSupModel.getSupplierId() + createSupModel.getSupplierName() + createSupModel.getAddress() + createSupModel.getPincode());
        System.out.println(createSupModel.getCity() + createSupModel.getCountry() + createSupModel.getEmail() + createSupModel.getPhone() + createSupModel.getCompanyName());
        supplierClientWS.createSupplier(createSupModel);
    }
}
