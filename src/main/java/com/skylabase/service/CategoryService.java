package com.skylabase.service;

import com.skylabase.model.Category;

public interface CategoryService extends GenericService<Category> {

    public Category findByName(String name);
}
