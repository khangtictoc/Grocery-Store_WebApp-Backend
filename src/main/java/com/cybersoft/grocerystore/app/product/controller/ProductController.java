package com.cybersoft.grocerystore.app.product.controller;

import com.cybersoft.grocerystore.app.product.dto.ProductDTO;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;
    private Logger logger = LoggerFactory.getLogger(ProductController.class);
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
    @GetMapping("getByCategory")
    public ResponseEntity<?> getAllByCategory(@RequestParam int categoryId){


        List<ProductEntity> listProduct = productServiceImp.findAll();
        List<ProductDTO> listProductDTO = new ArrayList<>();

        for(ProductEntity productItem : listProduct){
            ProductDTO productDTO = new ProductDTO();
            if(productItem.getCategory().getId()==categoryId)
            {
                productDTO.setId(productItem.getId());
                productDTO.setCategoryId(productItem.getCategory().getId());
                productDTO.setImage(productItem.getImage());
                productDTO.setName(productItem.getName());
                productDTO.setUnit(productItem.getUnit());
                productDTO.setPrice(productItem.getPrice());
                productDTO.setQuantity(productItem.getQuantity());
                productDTO.setAverageRating(productItem.getAverageRating());
                productDTO.setDescription(productItem.getDescription());
                productDTO.setOriginalPrice(productItem.getOriginalPrice());
                productDTO.setDiscountPercent(productItem.getDiscountPercent());
                productDTO.setActivated(true);
                listProductDTO.add(productDTO);
            }
        }

        BaseResponse baseResponse = new BaseResponse(
                200,
                "Retrieve all category successfully",
                listProductDTO
        );

        logger.info("Kiem tra: "+baseResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
    @GetMapping("getAllOrderByQuantity")
    public ResponseEntity<?> getAllOrderByQuantity(){

        List<Integer> ListOrderGroupBy = productServiceImp.findAllGroupByProduct();
        List<ProductEntity> listProduct = productServiceImp.findAllByOrderByQuantity();
        List<ProductDTO> listProductDTO = new ArrayList<>();
        for(int productIdOrderByQuantity : ListOrderGroupBy){
            for(ProductEntity productItem : listProduct){
                if(productItem.getId()==productIdOrderByQuantity)
                {
                    ProductDTO productDTO = new ProductDTO();
                    {
                        productDTO.setId(productItem.getId());
                        productDTO.setCategoryId(productItem.getCategory().getId());
                        productDTO.setImage(productItem.getImage());
                        productDTO.setName(productItem.getName());
                        productDTO.setUnit(productItem.getUnit());
                        productDTO.setPrice(productItem.getPrice());
                        productDTO.setQuantity(productItem.getQuantity());
                        productDTO.setAverageRating(productItem.getAverageRating());
                        productDTO.setDescription(productItem.getDescription());
                        productDTO.setOriginalPrice(productItem.getOriginalPrice());
                        productDTO.setDiscountPercent(productItem.getDiscountPercent());
                        productDTO.setActivated(true);
                        listProductDTO.add(productDTO);
                    }
                }

            }
        }




        BaseResponse baseResponse = new BaseResponse(
                200,
                "Retrieve all category successfully",
                listProductDTO
        );

        logger.info("Kiem tra: "+baseResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
}
