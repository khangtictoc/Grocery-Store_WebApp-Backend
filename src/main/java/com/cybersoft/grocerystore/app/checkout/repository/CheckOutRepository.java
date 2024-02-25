package com.cybersoft.grocerystore.app.checkout.repository;

import com.cybersoft.grocerystore.app.checkout.entity.CheckOutEntity;
import com.cybersoft.grocerystore.app.user.entity.UserEntity;
import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOutEntity,Integer> {

    CheckOutEntity findFirstByUserOrderByCreatedDatetimeDesc(UserEntity user);

}
