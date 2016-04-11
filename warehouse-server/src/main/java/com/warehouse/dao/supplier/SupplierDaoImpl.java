
package com.warehouse.dao.supplier;

import org.springframework.stereotype.Repository;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.AbstractGenericDAOHibernate;
import com.warehouse.dao.AbstractHibernateUtil;
import com.warehouse.entity.Supplier;

@Repository
public class SupplierDaoImpl extends AbstractGenericDAOHibernate<Supplier, Long> implements SupplierDao {

    @Override
    public void createSupplier(Supplier supplier) throws WarehouseServerException {
        AbstractHibernateUtil.createSession(sessionFactory);
        AbstractHibernateUtil.beginTransaction();
        this.getSession().save(supplier);
        AbstractHibernateUtil.commit();
    }
}
