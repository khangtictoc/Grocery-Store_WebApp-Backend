package com.cybersoft.grocerystore.app.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    @Query(value="SELECT a.id_category,sum(b.S) from products as a join (SELECT id_product, sum(quantity) as S FROM order_detail group by id_product order by sum(quantity) desc) as b on a.id=b.id_product group by id_category  order by sum(b.S) desc  ", nativeQuery = true)
    List<Integer> findAllCategoryOrderByBestSeller();
}
