
package com.warehouse.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.warehouse.common.WarehouseServerException;

/**
 * T is the entity instance for which you're implementing a DAO . ID is the type
 * of Database identifier(Long,Integer etc.,)
 * 
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Find entities by an example instance . This will be helpful in search
     * functionalities .
     * 
     * @param exampleInstance
     * @param excludeProperty
     * @return list of entities matching
     * @throws PrezisionBaseException .
     */
    List<T> findByExample(T exampleInstance, String... excludeProperty) throws WarehouseServerException;

    /**
     * Save or Update the entity .
     * 
     * @param entity
     * @return entity
     * @throws PrezisionBaseException .
     */
    T saveOrUpdate(T entity) throws WarehouseServerException;

    /**
     * Delete the entity.
     * 
     * @param entity
     * @throws PrezisionBaseException .
     */

    void delete(T entity) throws WarehouseServerException;

    /**
     * Flush the session to sync with db .
     * 
     * @throws PrezisionBaseException .
     */
    void flush() throws WarehouseServerException;;

    /**
     * Clear the session to remove stale data .
     * 
     * @throws PrezisionBaseException .
     */
    void clear() throws WarehouseServerException;

    /**
     * Gets the size of the list using Projection on rowcount .
     * 
     * @param criterionAliasMap
     * @param criterion
     * @return Integer
     * @throws PrezisionBaseException .
     */
    public abstract Integer getSize(Map<String, String> criterionAliasMap, Criterion... criterion) throws WarehouseServerException;

    /**
     * Fetch records for a page .Used in Pagination .
     * 
     * @param firstRec
     * @param maxRec.
     * @param sortColumn
     * @param orderType
     * @param criterionAliasMap
     * @param criterion
     * @return list of entities
     * @throws PrezisionBaseException .
     */
    public abstract List<T> fetchPage(int firstRec, int maxRec, String sortColumn, String orderType, Map<String, String> criterionAliasMap, Criterion... criterion)
            throws WarehouseServerException;

}
