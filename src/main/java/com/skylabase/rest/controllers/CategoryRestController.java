package com.skylabase.rest.controllers;

import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories(@RequestParam(value = "name", defaultValue = "") String name) {
        if (categoryService.findAll() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryService.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (categoryService.exists(category)) {
            return new ResponseEntity<Category>(HttpStatus.CONFLICT);
        }
        final Category created = categoryService.create(category);
        return new ResponseEntity<Category>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody Category category) {
        final Category existing = categoryService.findById(id);
        if (existing == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        existing.setName(category.getName());

        categoryService.update(existing);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") String id) {
        final Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteAllCategories() {
        categoryService.deleteAll();
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
