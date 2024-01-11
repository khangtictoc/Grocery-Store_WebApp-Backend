package com.store.shoppy.app.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.store.shoppy.app.category.entity.CategoryEntity;
import com.store.shoppy.app.category.repository.CategoryRepository;
import com.store.shoppy.app.category.service.imp.CategoryServiceImp;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}
