package com.skylabase.service;

import com.skylabase.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    boolean exists(long productId);

    Product create(Product product);

    Product findById(long productId);

    Page<Product> findByFarmId(long farmId, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByNameLike(String name, Pageable pageable);

    Product update(Product product);

    void delete(long productId);
}