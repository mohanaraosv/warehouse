
package com.warehouse.dao.account;

import org.springframework.stereotype.Repository;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.AbstractGenericDAOHibernate;
import com.warehouse.dao.AbstractHibernateUtil;
import com.warehouse.entity.Account;

@Repository
public class AccountDaoImpl extends AbstractGenericDAOHibernate<Account, Long> implements AccountDao {

    @Override
    public void createAccount(Account account) throws WarehouseServerException {
        AbstractHibernateUtil.createSession(sessionFactory);
        AbstractHibernateUtil.beginTransaction();
        this.getSession().save(account);
        AbstractHibernateUtil.commit();
    }
}
