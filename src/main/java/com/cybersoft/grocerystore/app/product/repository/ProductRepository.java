package com.cybersoft.grocerystore.app.product.repository;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
