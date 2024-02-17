package com.cybersoft.grocerystore.app.category.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.category.repository.CategoryRepository;
import com.cybersoft.grocerystore.app.category.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {



        return categoryRepository.findAll();
    }

    public List<Integer> findAllCategoryOrderByBestSeller() {
        return categoryRepository.findAllCategoryOrderByBestSeller();
    }

}
