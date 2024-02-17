package com.cybersoft.grocerystore.app.product.repository;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    List<ProductEntity> findAllByOrderByQuantity();
    @Query("SELECT product.id FROM order_detail group by product.id order by sum(quantity) desc")
    List<Integer> findAllGroupByProduct();

}
