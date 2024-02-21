package com.cybersoft.grocerystore.app.product.controller;

import com.cybersoft.grocerystore.app.product.dto.ProductDTO;
import com.cybersoft.grocerystore.app.product.entity.ProductEntity;
import com.cybersoft.grocerystore.app.product.repository.ProductRepository;
import com.cybersoft.grocerystore.app.product.service.imp.ProductServiceImp;
import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductController.class);


    @PostMapping("updatebyid")
    public ResponseEntity<?> updateProduct(@RequestParam int id,
                                           @RequestParam MultipartFile file,
                                           @RequestParam String name,
                                           @RequestParam float price ,
                                           @RequestParam float originalPrice,
                                           @RequestParam float discountPercent ,
                                           @RequestParam int idCategory,
                                           @RequestParam(required = false) String unit,
                                           @RequestParam int quantity,
                                           @RequestParam double averageRating,
                                           @RequestParam(required = false) String description,
                                           @RequestParam(required = false) boolean isActivated
                                           ){

        productServiceImp.updateProductById(id,file, name, price, originalPrice, discountPercent, idCategory, unit, quantity, averageRating, description, isActivated);
        return new ResponseEntity<>("update product successfully", HttpStatus.OK);
    }


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
        List<ProductEntity> listProduct = productRepository.findAll();
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
        List<Integer> ListOrderGroupBy = productRepository.findAllGroupByProduct();
        List<ProductEntity> listProduct = productRepository.findAllByOrderByQuantity();
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
