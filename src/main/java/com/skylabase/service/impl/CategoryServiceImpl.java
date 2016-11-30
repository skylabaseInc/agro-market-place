package com.skylabase.service.impl;

import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RestController for that handles requests {@link Category} objects
 *
 * @see Category
 * @see CategoryRepository
 */
@Service
class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get an element of type Category with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Category findById(String id) {
        return categoryRepository.findOne(id);
    }

    /**
     * Get an element of type Category with given name.
     *
     * @param name the id of the element to get
     * @return the element if found
     */
    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Get all elements of type Category.
     *
     * @return the list of Category elements if found
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Create a Category element.
     *
     * @param category the Category object to save.
     * @return the category if succesfully saved.
     */
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

    Category findByName(@Param("name") String name);
}