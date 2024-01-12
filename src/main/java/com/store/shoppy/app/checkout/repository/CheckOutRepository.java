package com.store.shoppy.app.checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shoppy.app.checkout.entity.CheckOutEntity;
@Repository
public interface CheckOutRepository extends JpaRepository<CheckOutEntity,Integer> {
}
