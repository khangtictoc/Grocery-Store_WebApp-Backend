package com.cybersoft.grocerystore.app.order.service.Imp;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.order.service.OrderService;

import java.util.List;

public interface OrderServiceImp {

    List<OrderDetailEntity> listCompletedOrder();
    void add(int idProduct, int idUser, float purchasePrice, int quantity);
    void update(int id, int idProduct, int idUser, float purchasePrice, int quantity,boolean isChecked);
    void updateIsChecked(int id, boolean isChecked);
    List<Integer> getListIdOrderByIdUser(int idUser, boolean isChecked);
    float calculateTotalPriceByIdUser(int idUser, boolean isChecked);
    OrderDetailEntity getOrderById(int id);


}
