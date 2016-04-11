
package com.warehouse.ws.supplier;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Supplier;

public interface SupplierServiceWS {

    @POST
    @Path("/createSupplier")
    @Produces(MediaType.APPLICATION_XML)
    public Response createSupplier(Supplier createSupModel) throws WarehouseServerException;
}
