package com.skylabase.rest.controllers;

import com.skylabase.exceptions.InvalidParameterException;
import com.skylabase.model.Category;
import com.skylabase.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoriesRestController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new category", notes = "Returns the created category.")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        final Category created = categoryService.create(category);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ApiOperation(value = "Get categories", notes = "Returns a list of all categories.")
    public ResponseEntity<Page<Category>> getCategories(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                        @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Category> categories;
        if (!name.isEmpty()) {
            categories = categoryService.findByName(name, new PageRequest(pgNum, pgSize));
        } else {
            categories = categoryService.findAll(new PageRequest(pgNum, pgSize));
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get category", notes = "Returns a category.")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") long categoryId) {
        Category category = categoryService.findById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/farms/{farmId}/products/{productId}/categories", method = RequestMethod.GET)
    @ApiOperation(value = "Get categories", notes = "Returns a list of all categories.")
    public ResponseEntity<Page<Category>> getCategoriesByProduct(@PathVariable(value = "userId") long userId,
                                                                 @PathVariable(value = "farmId") long farmId,
                                                                 @PathVariable(value = "productId") long productId,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                                 @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Category> categories = categoryService.findByProductId(productId, new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing category", notes = "Returns the updated category.")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "categoryId") long categoryId,
                                                   @RequestBody Category category) {
        if (categoryId != category.getId()) {
            throw new InvalidParameterException("'categoryId' does not match 'category.getId()'");
        }
        category = categoryService.update(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a category")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}