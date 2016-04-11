
package com.warehouse.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Supplier;

@Service
public class SupplierClientWSImpl extends WSImpl implements SupplierClientWS {

    private static String URI = "/create/createSupplierService";

    @Override
    public Supplier createSupplier(Supplier createSupModel) throws WarehouseClientException {
        WebClient client = WebClient.create("http://localhost:8085" + URI);
        client.path("/createSupplierInfo");
        client.accept(MediaType.APPLICATION_XML);
        System.out.println("model" + createSupModel.getSupplierId() + createSupModel.getSupplierName() + createSupModel.getAddress() + createSupModel.getPincode());
        System.out.println(
                "model" + createSupModel.getCity() + createSupModel.getCountry() + createSupModel.getEmail() + createSupModel.getPhone() + createSupModel.getCompanyName());
        Response response = client.post(createSupModel);
        return handleResponce(Supplier.class, response);
    }
}
