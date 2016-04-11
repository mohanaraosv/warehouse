
package com.warehouse.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Account;

@Service
public class AccountClientWSImpl extends WSImpl implements AccountClientWS {

    private static String URI = "/warehouseServer/";

    @Override
    public Account createAccount(Account newModel) throws WarehouseClientException {
        WebClient client = WebClient.create("http://localhost:8085" + URI);
        client.path("account");
        client.accept(MediaType.APPLICATION_XML);
        System.out.println("model" + newModel.getUserName() + newModel.getPassword() + newModel.getFirstName() + newModel.getLastName() + newModel.getEmail()
                + newModel.getContactNumber() + newModel.getCity() + newModel.getCountry());
        Response response = client.post(newModel);
        return handleResponce(Account.class, response);
    }
}
