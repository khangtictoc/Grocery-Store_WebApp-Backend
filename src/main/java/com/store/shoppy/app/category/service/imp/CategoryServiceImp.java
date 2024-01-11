package com.store.shoppy.app.category.service.imp;

import com.store.shoppy.app.category.entity.CategoryEntity;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryEntity> findAll();
}
