package com.cybersoft.grocerystore.app.category.service;

import com.cybersoft.grocerystore.app.category.entity.CategoryEntity;
import com.cybersoft.grocerystore.app.category.repository.CategoryRepository;
import com.cybersoft.grocerystore.app.category.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void add(String name) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        try{
            categoryRepository.save(category);
        }catch (Exception e) {
            throw new RuntimeException("Loi save category");
        }
    }

    @Override
    public void deleteCategoryById(int id) {
        CategoryEntity category = findCategoryById(id);
        try{
            categoryRepository.delete(category);
        }catch(Exception e ){
            throw new RuntimeException("Loi xoa category by id "+id);
        }
    }

    @Override
    public void updateCategoryById(int id, String name) {
        CategoryEntity category = findCategoryById(id);
        category.setName(name);
        try{
            categoryRepository.save(category);
        }catch(Exception e ){
            throw new RuntimeException("Loi update category by id "+id);
        }
    }

    @Override
    public CategoryEntity findCategoryById(int id) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new RuntimeException("Khong tim thay category by id "+id);
    }

}
