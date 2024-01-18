package com.store.shoppy.app.category.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.store.shoppy.app.category.service.imp.CategoryServiceImp;
import com.store.shoppy.app.category.dto.CategoryDTO;
import com.store.shoppy.app.category.entity.CategoryEntity;
import com.store.shoppy.libraries.payload.response.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    HttpServletRequest request;
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("getall")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryEntity> listCategory = categoryServiceImp.findAll();
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();

        for(CategoryEntity categoryItem : listCategory){
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setId(categoryItem.getId());
            categoryDTO.setCategoryName(categoryItem.getName());

            listCategoryDTO.add(categoryDTO);
        }

        BaseResponse baseResponse = new BaseResponse(
            200,
            "Retrieve all category successfully",
            listCategoryDTO
        );

        logger.info("Kiem tra: "+baseResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

}
