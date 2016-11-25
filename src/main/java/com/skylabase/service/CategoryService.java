package com.skylabase.service;


import com.skylabase.model.Category;

import java.util.List;

public interface CategoryService {

    /**
     * Find a category by Id.
     *
     * @param id the id of the category to get
     * @return the category object if found else return null
     */
    public Category findById(String id);

    /**
     * Get a list of all categories from the system.
     *
     * @return list containing all categories in the system
     */
    public List<Category> findAll();

    /**
     * Creates a new category in the system.
     *
     * @param category the category to create
     * @return the category created
     */
    public Category create(Category category);

    /**
     * Updates and existing category.
     *
     * @param category to be updated
     * @return the updated category
     */
    public Category update(Category category);

    /**
     * Deletes a category from the system.
     *
     * @param category the category to be deleted
     */
    public void delete(Category category);

    /**
     * Checks if given category exists.
     *
     * @param category the category been checked for existence
     * @return true if category exist else false
     */
    public boolean exists(Category category);

    /**
     * Delete all category from the system.
     */
    public void deleteAll();
}
