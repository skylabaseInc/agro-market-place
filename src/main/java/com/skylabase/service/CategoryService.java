package com.skylabase.service;

import com.skylabase.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category create(Category category);

    Page<Category> findAll(Pageable pageable);

    Category findById(long id);

    Page<Category> findByName(String name, Pageable pageable);

    Page<Category> findByProductId(long productId, Pageable pageable);

    Category update(Category category);

    void delete(long categoryId);

    boolean exists(long categoryId);
}
