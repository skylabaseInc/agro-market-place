package com.skylabase.agromarketplace.service;

import com.skylabase.agromarketplace.model.Category;

public interface CategoryService extends GenericService<Category> {

    public Category findByName(String name);
}
