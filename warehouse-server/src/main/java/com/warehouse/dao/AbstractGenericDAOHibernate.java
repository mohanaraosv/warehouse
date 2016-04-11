
package com.warehouse.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.common.HQLQueryManager;
import com.warehouse.common.QueryHQL;
import com.warehouse.common.Utility;
import com.warehouse.common.WarehouseServerException;

/**
 * This Class contains all the common CRUD Hibernate Operations .
 * 
 * 
 * @param <T>
 * @param <ID>
 */
public class AbstractGenericDAOHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private static final Logger LOGGER = Logger.getLogger(AbstractGenericDAOHibernate.class);

    @Autowired
    public SessionFactory sessionFactory;

    private Class<T> persistentClass;

    private Session session;

    /**
     * Constructor which retrieves the class name from the generic arguments .
     * 
     **/
    @SuppressWarnings("unchecked")
    public AbstractGenericDAOHibernate() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Get the current Hibernate session.
     * 
     * @return session
     * @throws WarehouseServerException
     */
    public Session getSession() throws WarehouseServerException {
        if (session == null) {
            session = AbstractHibernateUtil.currentSession();
            if (session == null) {
                session = AbstractHibernateUtil.createSession(sessionFactory);
            }
        }
        if (!session.isOpen()) {
            session = null;
            session = getSession();
        }

        return session;
    }

    /**
     * Setter for Hibernate session.
     * 
     * @param session
     *            .
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Getter for persistentClass .
     * 
     * @return class
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * Find an entity based on id.
     * 
     * @param entityId
     *            .
     * @return entity
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public T findById(ID entityId) throws WarehouseServerException {
        T entity = null;
        try {
            entity = (T) getSession().get(getPersistentClass(), entityId);
        } catch (JDBCException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }

        return entity;
    }

    /**
     * Find entities by an example instance . This will be helpful in search
     * functionalities .
     * 
     * @param exampleInstance
     *            .
     * @param excludeProperty
     *            .
     * @return list of entities matching
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            Example example = Example.create(exampleInstance);
            example.enableLike(MatchMode.ANYWHERE);
            example.excludeZeroes();
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            crit.add(example);
        } catch (JDBCException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * Save or Update the entity .
     * 
     * @param entity
     *            .
     * @return entity
     * @throws SelfLendingServiceException .
     */
    public T saveOrUpdate(T entity) throws WarehouseServerException {
        try {
            getSession().saveOrUpdate(entity);
        } catch (ConstraintViolationException cve) {
            LOGGER.error("Error in saveOrUpdate", cve);
            throw new WarehouseServerException(cve.getMessage(), cve.getErrorCode(), cve);
        } catch (JDBCException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return entity;
    }

    /**
     * Save or Update the entity .
     * 
     * @param entity
     *            .
     * @return entity
     * @throws SelfLendingServiceException .
     */
    public T save(T entity) throws WarehouseServerException {
        try {
            getSession().save(entity);
        } catch (ConstraintViolationException cve) {
            LOGGER.error("Error in save", cve);
            throw new WarehouseServerException(cve.getMessage(), cve.getErrorCode(), cve);
        } catch (JDBCException he) {
            LOGGER.error("Error in save", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in save", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return entity;
    }

    /**
     * Delete the entity.
     * 
     * @param entity
     *            .
     * @throws SelfLendingServiceException .
     */

    public void delete(T entity) throws WarehouseServerException {
        try {
            getSession().delete(entity);
        } catch (JDBCException he) {
            LOGGER.error("Error in delete", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in delete", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }

    }

    /**
     * Flush the session to sync with db .
     * 
     * @throws SelfLendingServiceException .
     */
    public void flush() throws WarehouseServerException {
        try {
            getSession().flush();
        } catch (JDBCException he) {
            LOGGER.error("Error in flush", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in flush", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }

    }

    /**
     * Clear the session to remove stale data .
     * 
     * @throws SelfLendingServiceException .
     */
    public void clear() throws WarehouseServerException {
        try {
            getSession().clear();
        } catch (JDBCException he) {
            LOGGER.error("Error in clear", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in clear", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }

    }

    /**
     * This method takes variable arguments if exists and creates a query with .
     * the where conditions passed in and gives the objects list .
     * 
     * @param criterion
     *            varargs.
     * @return list of entities
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * Fetch records for a page .Used in Pagination .
     * 
     * @param firstRec
     * @param maxRec
     * @param sortColumn
     * @param orderType
     * @param criterionAliasMap
     * @param criterion
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List<T> fetchPage(int firstRec, int maxRec, String sortColumn, String orderType, Map<String, String> criterionAliasMap, Criterion... criterion)
            throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                if (c != null) {
                    crit.add(c);
                }
            }
            if (criterionAliasMap != null && !criterionAliasMap.isEmpty()) {
                Set criterionAliasMapKeySet = criterionAliasMap.keySet();
                Iterator itr = criterionAliasMapKeySet.iterator();
                String mapKey = "";
                String mapValue = "";
                while (itr.hasNext()) {
                    mapKey = itr.next().toString();
                    mapValue = criterionAliasMap.get(mapKey);
                    crit.createAlias(mapKey, mapValue);
                }
            }
            if ("asc".equals(orderType)) {
                crit.addOrder(Order.asc(sortColumn));
            } else {
                crit.addOrder(Order.desc(sortColumn));
            }
            crit.setFirstResult(firstRec);
            crit.setMaxResults(maxRec);
        } catch (JDBCException he) {
            LOGGER.error("Error in fetchPage", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in fetchPage", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list Gets the size
     * of the list using Projection on rowcount.
     * 
     * @param criterionAliasMap
     * @param criterion
     * @return Integer
     * @throws SelfLendingServiceException .
     */
    // @SuppressWarnings("unchecked")
    public Integer getSize(Map<String, String> criterionAliasMap, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        Integer itemCount = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                if (c != null) {
                    crit.add(c);
                }
            }
            if (criterionAliasMap != null && !criterionAliasMap.isEmpty()) {
                Set<String> criterionAliasMapKeySet = criterionAliasMap.keySet();
                Iterator<String> itr = criterionAliasMapKeySet.iterator();
                String mapKey = "";
                String mapValue = "";
                while (itr.hasNext()) {
                    mapKey = itr.next().toString();
                    mapValue = criterionAliasMap.get(mapKey);
                    crit.createAlias(mapKey, mapValue);
                }
            }
            crit.setProjection(Projections.rowCount());
            itemCount = (Integer) crit.uniqueResult();

        } catch (JDBCException he) {
            LOGGER.error("Error in getSize", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in getSize", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return itemCount;
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list
     * 
     * @param criterion varargs
     * @return entity
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public T findByUniqueCriteria(Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByUniqueCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByUniqueCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return (T) crit.uniqueResult();
    }

    /**
     * This method takes hql as argument executes the query and returns the
     * entities list. the where conditions passed in and gives the objects list
     * 
     * @param hql
     * @return list
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public List findByQuery(String hql) throws WarehouseServerException {
        Query query = null;
        try {
            query = getSession().createQuery(hql);
        } catch (JDBCException he) {
            LOGGER.error("Error in findByQuery", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByQuery", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return query.list();
    }

    /**
     * Method to load any generic entity
     * 
     * @param entityId
     * @param clazz
     * @return Object
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public Object findById(Long entityId, Class clazz) throws WarehouseServerException {
        Object entity = null;
        try {
            entity = (Object) getSession().get(clazz, entityId);
        } catch (JDBCException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return entity;
    }

    /**
     * This method takes hql and hql param,execute and return list .
     * 
     * @param hql
     * @param parameter
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List findByQuery(String hql, Long parameter) throws WarehouseServerException {
        Query query = null;
        try {
            query = getSession().createQuery(Utility.hqlToken(hql));
            query.setParameter("1", parameter);
        } catch (JDBCException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return query.list();
    }

    /**
     * This method takes hql and hql param,execute and return list .
     * 
     * @param hql
     * @param parameter
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List findByQuery(String hql, String parameter) throws WarehouseServerException {
        Query query = null;
        try {
            query = getSession().createQuery(Utility.hqlToken(hql));
            query.setParameter("1", parameter);
        } catch (JDBCException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findById", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return query.list();
    }

    /**
     * This is a generic method for getting the records from the database
     * 
     * @param session,strQuery,arlParams
     * @return recordList
     * @throws WarehouseServerException
     */
    public List findByQuery(String[] strQuery, List arlParams) throws WarehouseServerException {
        List<Object> recordList = new ArrayList<Object>();
        try {

            /**
             * call the GetQueryObject() method of the QueryManager which
             * returns the Query class
             * ============================================================
             */
            HQLQueryManager queryManager = new HQLQueryManager();
            QueryHQL queryInstance = queryManager.getQueryObject(strQuery, arlParams);
            /** =========================================================== */
            String strFinalQuery = queryInstance.getDynamicQuery();
            List laValidParams = queryInstance.getValidParams();

            /**
             * ================================================================
             */
            int i = 0;
            do {
                int iInindex = strFinalQuery.toUpperCase().indexOf(" IN ");
                if (iInindex > 0) {
                    String strQueryupToIN = strFinalQuery.substring(0, iInindex + 3);
                    StringTokenizer strz = new StringTokenizer(strQueryupToIN, "?");
                    i = strz.countTokens() - 1;
                    strQueryupToIN = Utility.replaceString(strQueryupToIN, " in", " |IN| ");
                    String strQueryupAfterIN = strFinalQuery.substring(iInindex + 3, strFinalQuery.length());
                    int iIdex = strQueryupAfterIN.indexOf("?");
                    if (iIdex == -1) {
                        break;
                    }
                    String strTemp1 = strQueryupAfterIN.substring(0, iIdex);
                    String strTemp2 = strQueryupAfterIN.substring(iIdex + 1, strQueryupAfterIN.length());
                    if (strQueryupAfterIN.substring(strQueryupAfterIN.indexOf("("), strQueryupAfterIN.indexOf(")")).toUpperCase().indexOf("SELECT ") > 0) {
                        String strReplacedValue = strTemp1 + "?" + strTemp2;
                        strFinalQuery = strQueryupToIN + strReplacedValue;
                    } else {
                        String strReplacedValue = strTemp1 + laValidParams.get(i) + strTemp2;
                        laValidParams.remove(i);
                        strFinalQuery = strQueryupToIN + strReplacedValue;
                        i = 0;
                    }
                } else {
                    break;
                }
            } while (i < laValidParams.size());
            /**
             * ================================================================
             */

            Query query = getSession().createQuery(Utility.hqlToken(Utility.replaceString(strFinalQuery, "|IN|", " IN ")));

            int iparaIndex = 1;
            for (int k = 0; k < laValidParams.size(); k++) {
                Object dataVal = laValidParams.get(k);
                if (dataVal instanceof String) {
                    query.setParameter(iparaIndex + "", (String) dataVal);
                } else if (dataVal instanceof Integer) {
                    query.setParameter(iparaIndex + "", ((Integer) dataVal).intValue());
                } else if (dataVal instanceof Character) {
                    query.setParameter(iparaIndex + "", ((Character) dataVal).charValue());
                } else if (dataVal instanceof Float) {
                    query.setParameter(iparaIndex + "", ((Float) dataVal).floatValue());
                } else if (dataVal instanceof java.util.Date) {
                    query.setParameter(iparaIndex + "", (java.util.Date) dataVal);
                } else if (dataVal instanceof java.sql.Date) {
                    query.setParameter(iparaIndex + "", (java.sql.Date) dataVal);
                } else if (dataVal instanceof Double) {
                    query.setParameter(iparaIndex + "", ((Double) dataVal).doubleValue());
                } else if (dataVal instanceof Long) {
                    query.setParameter(iparaIndex + "", ((Long) dataVal).longValue());
                } else if (dataVal instanceof Time) {
                    query.setParameter(iparaIndex + "", (Time) dataVal);
                } else if (dataVal instanceof Timestamp) {
                    query.setParameter(iparaIndex + "", (Timestamp) dataVal);
                } else if (dataVal instanceof BigDecimal) {
                    query.setParameter(iparaIndex + "", (BigDecimal) dataVal);
                } else if (dataVal instanceof Character) {
                    query.setParameter(iparaIndex + "", (Character) dataVal);
                } else if (dataVal instanceof Byte) {
                    query.setParameter(iparaIndex + "", (Byte) dataVal);
                }
                iparaIndex++;
            }
            recordList = (List) query.list();
        } catch (JDBCException he) {
            LOGGER.error("Error in findByQuery", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByQuery", he);
            throw new WarehouseServerException(he.getMessage(), he);
        } catch (RuntimeException he) {
            LOGGER.error("Error in findByQuery", he);
            throw new WarehouseServerException(he.getMessage(), 100, he);
        }
        return recordList;
    }

    /**
     * Generic method to execute a hql with positinal params
     * 
     * @param hql
     * @param params
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List findByQueryParams(String hql, Object... params) throws WarehouseServerException {
        Query query = null;
        try {
            query = getSession().createQuery(Utility.hqlToken(hql));
            int iparaIndex = 1;
            for (int i = 0; i < params.length; i++) {
                query.setParameter(iparaIndex + "", params[i]);
                iparaIndex++;
            }

        } catch (JDBCException he) {
            LOGGER.error("Error in findByQueryParams", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByQueryParams", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return query.list();
    }

    /**
     * This method takes hql query and query parameters as arguments and
     * executes the query . Applicable for Edit or Delete
     * 
     * @param hql
     * @param params
     * @throws SelfLendingServiceException .
     */
    public void updateOrDeleteQuery(String hql, Object... params) throws WarehouseServerException {
        Query query = null;
        try {
            query = getSession().createQuery(Utility.hqlToken(hql));
            int iparaIndex = 1;
            for (int i = 0; i < params.length; i++) {
                query.setParameter(iparaIndex + "", params[i]);
                iparaIndex++;
            }
            query.executeUpdate();
        } catch (ConstraintViolationException cve) {
            LOGGER.error("Error in updateOrDeleteQuery", cve);
            throw new WarehouseServerException(cve.getMessage(), cve.getErrorCode(), cve);
        } catch (JDBCException he) {
            LOGGER.error("Error in updateOrDeleteQuery", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in updateOrDeleteQuery", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
    }

    /**This method only for my transaction DAO
     * This method takes hql query and query parameters as arguments and
     * executes the query . Applicable for Edit or Delete
     * 
     * @param hql
     * @param params
     * @throws SelfLendingServiceException .
     */
    public int updateOrDeleteQueryTransaction(String hql, Object... params) throws WarehouseServerException {
        Query query = null;
        int ct = 0;
        try {
            query = getSession().createQuery(Utility.hqlToken(hql));
            int iparaIndex = 1;
            for (int i = 0; i < params.length; i++) {
                query.setParameter(iparaIndex + "", params[i]);
                iparaIndex++;
            }
            ct = query.executeUpdate();
        } catch (ConstraintViolationException cve) {
            LOGGER.error("Error in updateOrDeleteQueryTransaction", cve);
            throw new WarehouseServerException(cve.getMessage(), cve.getErrorCode(), cve);
        } catch (JDBCException he) {
            LOGGER.error("Error in updateOrDeleteQueryTransaction", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in updateOrDeleteQueryTransaction", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return ct;
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list
     * 
     * @param orderByPropertyNames String
     * @param criterion varargs
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(String orderByPropertyNames, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            if (!"".equals(Utility.checkNull(orderByPropertyNames))) {
                crit.addOrder(Order.asc(orderByPropertyNames));
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list
     * 
     * @param orderByPropertyNames String
     * @param orderType String
     * @param criterionAliasMap Map
     * @param criterion varargs
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(String orderByPropertyNames, String orderType, Map<String, String> criterionAliasMap, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            if (criterionAliasMap != null && !criterionAliasMap.isEmpty()) {
                Set criterionAliasMapKeySet = criterionAliasMap.keySet();
                Iterator itr = criterionAliasMapKeySet.iterator();
                String mapKey = "";
                String mapValue = "";
                while (itr.hasNext()) {
                    mapKey = itr.next().toString();
                    mapValue = criterionAliasMap.get(mapKey);
                    crit.createAlias(mapKey, mapValue);
                }
            }
            if (!"".equals(Utility.checkNull(orderByPropertyNames))) {
                if ("asc".equals(orderType)) {
                    crit.addOrder(Order.asc(orderByPropertyNames));
                } else {
                    crit.addOrder(Order.desc(orderByPropertyNames));
                }
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * Find all objects of a given class .
     * 
     * @param clazz Class
     * @return list of objects
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public List findAll(Class clazz) throws WarehouseServerException {
        List<Object> objList;
        objList = findByCriteria(clazz);
        return objList;
    }

    /**
     * This method takes class names and variable arguments if exists and
     * creates a query with . the where conditions passed in and gives the
     * objects list .
     * 
     * @param clazz Class
     * @param criterion varargs
     * @return list of entities
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public List findByCriteria(Class clazz, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(clazz);
            for (Criterion c : criterion) {
                crit.add(c);
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * Save or Update the entity .
     * 
     * @param clazz
     * @param obj
     * @return entity
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public Object saveOrUpdate(Class clazz, Object obj) throws WarehouseServerException {
        try {
            getSession().saveOrUpdate(obj);
        } catch (ConstraintViolationException cve) {
            LOGGER.error("Error in saveOrUpdate", cve);
            throw new WarehouseServerException(cve.getMessage(), cve.getErrorCode(), cve);
        } catch (JDBCException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in saveOrUpdate", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return obj;
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list .
     * 
     * @param aliasMap Map
     * @param criterion varargs
     * @return list of entities
     * @throws SelfLendingServiceException .
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Map<String, String> aliasMap, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        try {
            crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            if (aliasMap != null && !aliasMap.isEmpty()) {
                Set criterionAliasMapKeySet = aliasMap.keySet();
                Iterator itr = criterionAliasMapKeySet.iterator();
                String mapKey = "";
                String mapValue = "";
                while (itr.hasNext()) {
                    mapKey = itr.next().toString();
                    mapValue = aliasMap.get(mapKey);
                    crit.createAlias(mapKey, mapValue);
                }
            }
        } catch (JDBCException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in findByCriteria", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return crit.list();
    }

    /**
     * This method takes variable arguments if exists and creates a query with
     * the where conditions passed in and gives the objects list Gets the size
     * of the list using Projection on rowcount .
     * 
     * @param clazz
     * @param criterionAliasMap
     * @param criterion
     * @return Integer
     * @throws WarehouseServerException
     */
    @SuppressWarnings("unchecked")
    public Integer getSize(Class clazz, Map<String, String> criterionAliasMap, Criterion... criterion) throws WarehouseServerException {
        Criteria crit = null;
        Integer itemCount = null;
        try {
            crit = getSession().createCriteria(clazz);

            if (criterionAliasMap != null && !criterionAliasMap.isEmpty()) {
                Set criterionAliasMapKeySet = criterionAliasMap.keySet();
                Iterator itr = criterionAliasMapKeySet.iterator();
                String mapKey = "";
                String mapValue = "";
                while (itr.hasNext()) {
                    mapKey = itr.next().toString();
                    mapValue = criterionAliasMap.get(mapKey);
                    crit.createAlias(mapKey, mapValue);
                }
            }

            for (Criterion c : criterion) {
                if (c != null) {
                    crit.add(c);
                }
            }
            crit.setProjection(Projections.rowCount());
            itemCount = (Integer) crit.uniqueResult();

        } catch (JDBCException he) {
            LOGGER.error("Error in getSize", he);
            throw new WarehouseServerException(he.getMessage(), he.getErrorCode(), he);
        } catch (HibernateException he) {
            LOGGER.error("Error in getSize", he);
            throw new WarehouseServerException(he.getMessage(), he);
        }
        return itemCount;
    }
}
