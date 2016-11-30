package com.skylabase.service.impl;

import com.skylabase.model.Product;
import com.skylabase.service.CategoryService;
import com.skylabase.service.FarmService;
import com.skylabase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FarmService farmService;

    @Autowired
    private CategoryService categoryService;

    private boolean productSourceFarmIdExists(String sourceFarmId) {
        return farmService.findById(sourceFarmId) != null;
    }

    private boolean productCategoryExists(String categoryId) {
        return categoryService.findById(categoryId) != null;
    }

    /**
     * Get an element of type Product with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Product findById(String id) {
        return productRepository.findOne(id);
    }

    /**
     * Get all elements of type Product in the system.
     *
     * @return list of all elements found
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Creates a new element of type Product in the system
     *
     * @param instance an instance of the element been created
     * @return the created element
     */
    @Override
    public Product create(Product instance) {
        if (exists(instance)) {
            // should throw exception: instance already exists.
            return null;
        }
        if (!productSourceFarmIdExists(instance.getFarmId())) {
            // Should throw exception: No Farm With Such an Id.
            return null;
        }
        List<String> categoryIds = instance.getCategoryIds();
        for (String id : categoryIds) {
            if (productCategoryExists(id)) {
                // Should throw exception: No Category With Such an Id.
                return null;
            }
        }
        return productRepository.save(instance);
    }

    /**
     * Update an existing element in the system.
     *
     * @param instance the updated version of the existing element
     * @return the updated version of the existing element
     */
    @Override
    public Product update(Product instance) {
        if (!productSourceFarmIdExists(instance.getFarmId())) {
            // TODO Should throw exception: No Farm With Such an Id.
            return null;
        }
        List<String> categoryIds = instance.getCategoryIds();
        for (String id : categoryIds) {
            if (productCategoryExists(id)) {
                // TODO Should throw exception: No Category With Such an Id.
                return null;
            }
        }
        return productRepository.save(instance);
    }

    /**
     * Deletes and element of type Product from the system.
     *
     * @param instance the element been deleted
     */
    @Override
    public void delete(Product instance) {
        productRepository.delete(instance);
    }

    /**
     * Checks if element exists in the system.
     *
     * @param instance the element
     * @return true if element exists else return false
     */
    @Override
    public boolean exists(Product instance) {
        if (instance.getId() == null) {
            return false;
        }
        return productRepository.exists(instance.getId());
    }

    /**
     * Delete all Products from the system.
     */
    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}

interface ProductRepository extends MongoRepository<Product, String> {
}