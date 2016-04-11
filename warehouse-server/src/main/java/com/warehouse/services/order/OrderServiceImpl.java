
package com.warehouse.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.common.WarehouseServerException;
import com.warehouse.dao.order.OrderDao;
import com.warehouse.entity.OrderDetails;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderDao orderDao;

    @Override
    public OrderDetails getOrderDetails(OrderDetails itemVO) throws WarehouseServerException {
        System.out.println(itemVO.getProductName());
        System.out.println(itemVO.getBarcode());
        System.out.println(itemVO.getDescription());
        System.out.println(itemVO.getQuantity());
        System.out.println(itemVO.getWeight());
        System.out.println(itemVO.getAmount());
        orderDao.saveOrder(itemVO);
        return itemVO;
    }

}
