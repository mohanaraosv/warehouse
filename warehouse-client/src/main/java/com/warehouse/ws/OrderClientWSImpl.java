
package com.warehouse.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Order;

@Service
public class OrderClientWSImpl extends WSImpl implements OrderClientWS {

    private static String URI = "/item/itemService";

    @Override
    public Order readOrderDetails(Order item) throws WarehouseClientException {

        WebClient client = WebClient.create("http://localhost:8085" + URI);
        client.path("/itemInfo");
        client.accept(MediaType.APPLICATION_XML);
        System.out.println("Model" + item.getProductName() + item.getBarcode() + item.getDescription() + item.getQuantity() + item.getWeight() + item.getAmount());
        Response response = client.post(item);
        return handleResponce(Order.class, response);
    }

}
