package com.cybersoft.grocerystore.app.category.service.imp;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryEntity> findAll();
}
