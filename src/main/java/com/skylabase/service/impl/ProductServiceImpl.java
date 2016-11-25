package com.skylabase.service.impl;

import com.skylabase.model.Product;
import com.skylabase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
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