package com.skylabase.service.impl;

import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(String id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
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
        if (category.getId() == null) {
            return false;
        }
        return categoryRepository.exists(category.getId());
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}

interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByName(@Param("name") String name);
}