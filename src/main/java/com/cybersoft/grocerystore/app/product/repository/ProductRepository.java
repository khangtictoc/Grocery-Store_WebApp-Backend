package com.cybersoft.grocerystore.app.product.repository;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

}
