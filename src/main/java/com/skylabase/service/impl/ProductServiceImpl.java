package com.skylabase.service.impl;

import com.skylabase.model.Category;
import com.skylabase.model.Farm;
import com.skylabase.model.Product;
import com.skylabase.service.CategoryService;
import com.skylabase.service.FarmService;
import com.skylabase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FarmService farmService;

    @Autowired
    private CategoryService categoryService;

    private boolean productSourceFarmIdExists(String sourceFarmId) {
        return farmService.findById(sourceFarmId) != null;
    }

    private boolean productCategoryExists(String category_id) {
        return categoryService.findById(category_id) != null;
    }

    /**
     * Get an element of type T with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Product findById(String id) {
        return productRepository.findOne(id);
    }

    /**
     * Get all elements of type T in the system.
     *
     * @return list of all elements found
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Creates a new element of type T in the system
     *
     * @param instance an instance of the element been created
     * @return the created element
     */
    @Override
    public Product create(Product instance) {
        if (!productSourceFarmIdExists(instance.getFarm_id())) {
            // Should throw exception: No Farm With Such an Id.
            return null;
        }
        List<String> category_ids = instance.getCategory_ids();
        for (String id : category_ids) {
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
        return create(instance);
    }

    /**
     * Deletes and element of type T from the system.
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
     * Delete all Ts from the system.
     */
    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}

interface ProductRepository extends MongoRepository<Product, String> {
}