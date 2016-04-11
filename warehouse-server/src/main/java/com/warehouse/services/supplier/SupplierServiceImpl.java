
package com.warehouse.services.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.supplier.SupplierDao;
import com.warehouse.entity.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public Supplier createSupplier(Supplier createSupModel) throws WarehouseServerException {
        System.out.println(createSupModel.getSupplierId() + createSupModel.getSupplierName() + createSupModel.getAddress() + createSupModel.getPincode());
        System.out.println(createSupModel.getCity() + createSupModel.getCountry() + createSupModel.getEmail() + createSupModel.getPhone() + createSupModel.getCompanyName());
        supplierDao.createSupplier(createSupModel);
        return createSupModel;
    }
}
