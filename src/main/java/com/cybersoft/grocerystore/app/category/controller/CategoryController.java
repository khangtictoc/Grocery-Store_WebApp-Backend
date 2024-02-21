package com.cybersoft.grocerystore.app.category.controller;



import com.cybersoft.grocerystore.libraries.payload.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.cybersoft.grocerystore.app.category.service.imp.CategoryServiceImp;
import com.cybersoft.grocerystore.app.category.dto.CategoryDTO;
import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    HttpServletRequest request;
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("add")
    public ResponseEntity<?> addCategory(@RequestParam String name){
        categoryServiceImp.add(name);
        return new ResponseEntity<>("Add category successfully",HttpStatus.OK);
    }
    @GetMapping("updatebyid")
    public ResponseEntity<?> updateCategoryById(@RequestParam int id,@RequestParam String name){
        categoryServiceImp.updateCategoryById(id,name);
        return new ResponseEntity<>("Update category successfully",HttpStatus.OK);
    }
    @GetMapping("deletebyid")
    public ResponseEntity<?> deleteCategoryById(@RequestParam int id){
        categoryServiceImp.deleteCategoryById(id);
        return new ResponseEntity<>("Delete category successfully",HttpStatus.OK);
    }

    @GetMapping("getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){

        CategoryEntity category = categoryServiceImp.findCategoryById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(category);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

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
    @GetMapping("getallOrderBySold")
    public ResponseEntity<?> getallOrderBySold(){

        List<Integer> listIdOrderBySold = categoryServiceImp.findAllCategoryOrderByBestSeller();
        List<CategoryEntity> listCategory = categoryServiceImp.findAll();
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();
        for(int categoryIdOrderBySold : listIdOrderBySold){
            System.out.println("abavava"+categoryIdOrderBySold);
            for(CategoryEntity categoryItem : listCategory){
                if(categoryItem.getId()==categoryIdOrderBySold)
                {
                    CategoryDTO categoryDTO = new CategoryDTO();

                    categoryDTO.setId(categoryItem.getId());
                    categoryDTO.setCategoryName(categoryItem.getName());

                    listCategoryDTO.add(categoryDTO);
                }

            }
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
