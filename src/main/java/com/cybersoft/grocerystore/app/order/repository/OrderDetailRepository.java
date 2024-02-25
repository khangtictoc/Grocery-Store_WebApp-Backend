package com.cybersoft.grocerystore.app.order.repository;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Integer> {

    List<OrderDetailEntity> findOrderDetailEntityByUser(UserEntity user);


}
