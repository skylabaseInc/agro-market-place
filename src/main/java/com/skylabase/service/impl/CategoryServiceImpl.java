package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Category findById(@Param("id") long id);

    Page<Category> findByNameLike(@Param("name") String name, Pageable pageable);

    Page<Category> findByName(@Param("name") String name, Pageable pageable);

    Page<Category> findByProductId(@Param("product_id") long productId, Pageable pageable);
}

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
     * Create a Category element.
     *
     * @param category the Category object to save.
     * @return the category if succesfully saved.
     */
    @Override
    public Category create(Category category) {
        if (exists(category.getId())) {
            throw new ItemAlreadyExistsException("cannot create already existing Category");
        }
        return categoryRepository.save(category);
    }

    /**
     * Get all elements of type Category.
     *
     * @param pageable
     * @return the list of Category elements if found
     */
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    /**
     * Get an element of type Category with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Get an element of type Category with given name.
     *
     * @param name the id of the element to get
     * @return the element if found
     */
    @Override
    public Page<Category> findByName(String name, Pageable pageable) {
        return categoryRepository.findByNameLike(name, pageable);
    }

    @Override
    public Page<Category> findByProductId(long productId, Pageable pageable) {
        return categoryRepository.findByProductId(productId, pageable);
    }

    @Override
    public Category update(Category category) {
        if (!exists(category.getId())) {
            throw new ItemNotFoundException("cannot update a Category which does not exist");
        }
        return create(category);
    }

    @Override
    public void delete(long categoryId) {
        categoryRepository.delete(categoryId);
    }

    @Override
    public boolean exists(long categoryId) {
        return categoryRepository.exists(categoryId);
    }
}