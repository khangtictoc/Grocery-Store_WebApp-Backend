package com.cybersoft.grocerystore.app.product.controller;

import com.cybersoft.grocerystore.app.product.dto.ProductDTO;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
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

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        ProductDTO productDTO =productServiceImp.getProductById(id);

        BaseResponse baseResponse = new BaseResponse(200,"",productDTO);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAllProduct(){
        List<ProductEntity> listProduct =productServiceImp.getAllProducts();

        BaseResponse baseResponse = new BaseResponse(200,"",listProduct);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @GetMapping("getbestsalerproductsbycategory")
    public ResponseEntity<?> getBestSellerProductsByCategory(@RequestParam int idCategory, @RequestParam int topNumber){

        List<ProductDTO> listProduct = productServiceImp.getBestSellerProductsByCategory(idCategory, topNumber);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listProduct);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
