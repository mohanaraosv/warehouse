
package com.warehouse.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseClientException;
import com.warehouse.model.Purchase;

@Service
public class PurchaseClientWSImpl extends WSImpl implements PurchaseClientWS {

    private static String URI = "/newPurchase/newPurchaseService";

    @Override
    public Purchase createPurchase(Purchase newModel) throws WarehouseClientException {

        WebClient client = WebClient.create("http://localhost:8085" + URI);
        client.path("/newPurchaseInfo");
        client.accept(MediaType.APPLICATION_XML);
        System.out.println("model" + newModel.getOrderDate() + newModel.getSupplier() + newModel.getDeliveryDate());
        Response response = client.post(newModel);
        return handleResponce(Purchase.class, response);
    }

}
