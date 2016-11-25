package com.skylabase.service;

import com.skylabase.model.Category;
import java.util.List;

public interface CategoryService extends GenericService<Category> {

    public List<Category> findByName(String name);
}
