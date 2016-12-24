package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Product;
import com.skylabase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Product findById(@Param("productId") long productId);

    Page<Product> findByFarmId(@Param("farmId") long farmId, Pageable pageable);

    Page<Product> findByNameLike(@Param("name") String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Checks if element exists in the system.
     *
     * @param productId the element
     * @return true if element exists else return false
     */
    @Override
    public boolean exists(long productId) {
        return productRepository.exists(productId);
    }

    /**
     * Creates a new element of type Product in the system
     *
     * @param product an product of the element been created
     * @return the created element
     */
    @Override
    public Product create(Product product) {
        if (exists(product.getId())) {
            throw new ItemAlreadyExistsException("this product already exists");
        }
        return productRepository.save(product);
    }

    /**
     * Get an element of type Product with given productId.
     *
     * @param productId the productId of the element to get
     * @return the element if found
     */
    @Override
    public Product findById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Page<Product> findByFarmId(long farmId, Pageable pageable) {
        return productRepository.findByFarmId(farmId, pageable);
    }

    /**
     * Get all elements of type Product in the system.
     *
     * @return list of all elements found
     */
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByNameLike(String name, Pageable pageable) {
        return productRepository.findByNameLike(name, pageable);
    }

    /**
     * Update an existing element in the system.
     *
     * @param product the updated version of the existing element
     * @return the updated version of the existing element
     */
    @Override
    public Product update(Product product) {
        if (!exists(product.getId())) {
            throw new ItemNotFoundException("cannot update a product that does not exist");
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(long productId) {
        productRepository.delete(productId);
    }
}