package com.cybersoft.grocerystore.app.product.service.imp;

import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {

    void save(MultipartFile file);
    Resource load(String file);

    ProductEntity getProductById(int id);

}
