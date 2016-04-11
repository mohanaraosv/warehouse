
package com.warehouse.dao.order;

import org.springframework.stereotype.Repository;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.AbstractGenericDAOHibernate;
import com.warehouse.dao.AbstractHibernateUtil;
import com.warehouse.entity.OrderDetails;

@Repository
public class OrderDaoImpl extends AbstractGenericDAOHibernate<OrderDetails, Long> implements OrderDao {

    @Override
    public void saveOrder(OrderDetails item) throws WarehouseServerException {
        AbstractHibernateUtil.createSession(sessionFactory);
        AbstractHibernateUtil.beginTransaction();
        this.getSession().save(item);
        AbstractHibernateUtil.commit();
    }
}
