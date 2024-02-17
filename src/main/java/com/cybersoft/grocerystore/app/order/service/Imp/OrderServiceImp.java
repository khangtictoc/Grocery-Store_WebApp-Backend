package com.cybersoft.grocerystore.app.order.service.Imp;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;

import java.util.List;

public interface OrderServiceImp {

    List<OrderDetailEntity> listCompletedOrder();


}
