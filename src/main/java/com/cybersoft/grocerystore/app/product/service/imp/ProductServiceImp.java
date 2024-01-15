package com.cybersoft.grocerystore.app.product.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface ProductServiceImp {
    void save(MultipartFile file, String name, float price , float originalPrice,float discountPercent , int idCategory,String unit,int quantity,double averageRating,String description);
}
