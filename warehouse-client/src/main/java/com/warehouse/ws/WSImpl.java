
package com.warehouse.ws;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.warehouse.common.RestServicesException;
import com.warehouse.common.WarehouseClientException;

public class WSImpl {
    /**
     * @param c
     * @param response
     * @return
     * @throws WarehouseClientException
     */
    protected <T> T handleResponce(Class<T> entityType, Response response) throws WarehouseClientException {
        if (response.getStatus() == 200) {
            return response.readEntity(entityType);
        } else {
            RestServicesException restServicesException = response.readEntity(RestServicesException.class);
            throw new WarehouseClientException(restServicesException.getErrorCode(), restServicesException.getErrorMessage());
        }
    }

    /**
     * @param genericType
     * @param response
     * @return
     * @throws WarehouseClientException
     */
    protected <T> T handleResponce(GenericType<T> entityType, Response response) throws WarehouseClientException {
        if (response.getStatus() == 200) {
            return response.readEntity(entityType);
        } else {
            RestServicesException restServicesException = response.readEntity(RestServicesException.class);
            throw new WarehouseClientException(restServicesException.getErrorCode(), restServicesException.getErrorMessage());
        }
    }
}
