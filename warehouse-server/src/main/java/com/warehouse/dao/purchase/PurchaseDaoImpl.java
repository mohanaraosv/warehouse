
package com.warehouse.dao.purchase;

import org.springframework.stereotype.Repository;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.AbstractGenericDAOHibernate;
import com.warehouse.dao.AbstractHibernateUtil;
import com.warehouse.entity.Purchase;

@Repository
public class PurchaseDaoImpl extends AbstractGenericDAOHibernate<Purchase, Long> implements PurchaseDao {

    @Override
    public void createPurchase(Purchase model) throws WarehouseServerException {
        AbstractHibernateUtil.createSession(sessionFactory);
        AbstractHibernateUtil.beginTransaction();
        this.getSession().save(model);
        AbstractHibernateUtil.commit();
    }
}
