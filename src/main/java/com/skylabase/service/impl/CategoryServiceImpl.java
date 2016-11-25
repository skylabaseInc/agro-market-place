package com.skylabase.service.impl;

import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(String id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return create(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public boolean exists(Category category) {
        return categoryRepository.exists(category.getId());
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}

interface CategoryRepository extends MongoRepository<Category, String> {
}