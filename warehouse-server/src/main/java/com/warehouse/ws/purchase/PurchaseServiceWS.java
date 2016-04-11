
package com.warehouse.ws.purchase;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Purchase;


public interface PurchaseServiceWS {

    @POST
    @Path("/createPurchase")
    @Produces(MediaType.APPLICATION_XML)
    public Response createPurchase(Purchase purchase) throws WarehouseServerException;

}
