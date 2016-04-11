
package com.warehouse.ws.account;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.entity.Account;


public interface AccountServiceWS {

    @POST
    @Path("/createAccount")
    @Produces(MediaType.APPLICATION_XML)
    public Response createAccount(Account account) throws WarehouseServerException;

}
