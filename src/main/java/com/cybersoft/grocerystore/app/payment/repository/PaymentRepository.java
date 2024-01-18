package com.cybersoft.grocerystore.app.payment.repository;

import com.cybersoft.grocerystore.app.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

}
