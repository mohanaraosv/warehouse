
package com.warehouse.ws.supplier;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Supplier;
import com.warehouse.services.supplier.SupplierService;

@Service("supplierServiceWS")
public class SupplierServiceWSImpl implements SupplierServiceWS {

    @Autowired
    private SupplierService createSupplierService;

    @Override
    public Response createSupplier(Supplier createSupModel) throws WarehouseServerException {
        System.out.println(createSupModel.getSupplierId() + createSupModel.getSupplierName() + createSupModel.getAddress() + createSupModel.getPincode() + "33333333333");
        System.out.println(createSupModel.getCity() + createSupModel.getCountry() + createSupModel.getEmail() + createSupModel.getEmail() + createSupModel.getPhone()
                + createSupModel.getCompanyName() + "33333333333");
        createSupplierService.createSupplier(createSupModel);
        return null;
    }
}
