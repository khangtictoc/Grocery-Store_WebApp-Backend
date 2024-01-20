package com.cybersoft.grocerystore.app.product.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.service.imp.FileServiceImp;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.grocerystore.app.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    // rollbackFor: dùng để custom transactional lắng nghe exception nào
    // dat @transactional ngay truoc service hoac repository
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void save(MultipartFile file, String name, float price, float originalPrice, float discountPercent, int idCategory, String unit, int quantity, double averageRating, String description) {

            fileServiceImp.save(file);
            CategoryEntity category = new CategoryEntity();
            category.setId(idCategory);

            ProductEntity product = new ProductEntity();
            product.setName(name);
            product.setPrice(price);
            product.setOriginalPrice(originalPrice);
            product.setDiscountPercent(discountPercent);
            product.setCategory(category);
            product.setImage(file.getOriginalFilename());
            product.setUnit(unit);
            product.setQuantity(quantity);
            product.setAverageRating(averageRating);
            product.setDescription(description);

        try{
            productRepository.save(product);
        }catch(Exception e){
            throw new RuntimeException("Loi insert product: "+e.getMessage());
        }
    }

    @Override
    public ProductEntity getProductById(int id) {

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){

            return optionalProductEntity.get();

        }

        throw new RuntimeException("Khong tim thay product");

    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}
