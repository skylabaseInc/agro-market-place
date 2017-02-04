package com.skylabase.agromarketplace.service;

import com.skylabase.agromarketplace.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends GenericService<Product> {

    Page<Product> listAllByPage(Pageable pageable);
}