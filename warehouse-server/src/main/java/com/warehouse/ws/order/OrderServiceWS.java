
package com.warehouse.ws.order;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.OrderDetails;

public interface OrderServiceWS {
    @POST
    @Path("/readOrderDetails")
    @Produces(MediaType.APPLICATION_XML)
    public Response readOrderDetails(OrderDetails itemModel) throws WarehouseServerException;
}
