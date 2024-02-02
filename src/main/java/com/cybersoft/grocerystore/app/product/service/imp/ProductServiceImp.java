package com.cybersoft.grocerystore.app.product.service.imp;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    void save(MultipartFile file, String name, float price , float originalPrice,float discountPercent , int idCategory,String unit,int quantity,double averageRating,String description);
    List<ProductEntity> findAll();
    List<ProductEntity> findAllByOrderByQuantity();
    List<Integer> findAllGroupByProduct();
}
