package com.cybersoft.grocerystore.app.category.service.imp;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.role.entity.RoleEntity;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryEntity> findAll();
    List<Integer> findAllCategoryOrderByBestSeller();

    void add(String name);
    void deleteCategoryById(int id);
    void updateCategoryById(int id, String name);
    CategoryEntity findCategoryById(int id);



}
