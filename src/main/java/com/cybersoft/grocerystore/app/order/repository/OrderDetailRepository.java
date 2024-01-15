package com.cybersoft.grocerystore.app.order.repository;

import com.cybersoft.grocerystore.app.order.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Integer> {
}
