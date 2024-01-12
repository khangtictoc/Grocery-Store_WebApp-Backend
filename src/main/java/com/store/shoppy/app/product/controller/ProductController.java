package com.store.shoppy.app.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.store.shoppy.app.product.service.imp.ProductServiceImp;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @PostMapping("insert")
    public ResponseEntity<?> insertProduct(@RequestParam MultipartFile file,
                                           @RequestParam String name,
                                           @RequestParam float price ,
                                           @RequestParam float originalPrice,
                                           @RequestParam float discountPercent ,
                                           @RequestParam int idCategory,
                                           @RequestParam(required = false) String unit,
                                           @RequestParam int quantity,
                                           @RequestParam double averageRating,
                                           @RequestParam(required = false) String description){
        productServiceImp.save(file, name, price, originalPrice, discountPercent, idCategory, unit, quantity, averageRating, description);
        return new ResponseEntity<>("insert product successfully", HttpStatus.OK);
    }

}
