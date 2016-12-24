package com.skylabase.rest.controllers;

import com.skylabase.model.Product;
import com.skylabase.service.FarmService;
import com.skylabase.service.ProductService;
import com.skylabase.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "Products End Point.")
@RestController
@RequestMapping("/api/v1")
public class ProductsRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private FarmService farmService;

    @RequestMapping(value = "/users/{userId}/farms/{farmId}/products", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new product.", notes = "Must specify the owner and farm ids. Returns the created product.")
    public ResponseEntity<Product> createProduct(@PathVariable(value = "userId") long userId,
                                                 @PathVariable(value = "farmId") long farmId,
                                                 @RequestBody Product product) {
        final Product created = productService.create(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ApiOperation(value = "Get all Products, and optionally a list of Products whose names match the given name.",
            notes = "Can specify pageSize and pageNumber")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                     @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Product> products;
        if (!name.isEmpty()) {
            products = productService.findByNameLike(name, new PageRequest(pgNum, pgSize));
        } else {
            products = productService.findAll(new PageRequest(pgNum, pgSize));
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "users/{userId}/farms/{farmId}/products/{productId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get product", notes = "Returns a product.")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "userId") long userId,
                                              @PathVariable(value = "farmId") long farmId,
                                              @PathVariable(value = "productId") long productId) {
        Product product = productService.findById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "users/{userId}/farms/{farmId}/products", method = RequestMethod.GET)
    @ApiOperation(value = "Get products belonging to the User with id 'userId'", notes = "Returns a list of Products.")
    public ResponseEntity<Page<Product>> getProductsByFarm(@PathVariable(value = "userId") long userId,
                                                           @PathVariable(value = "farmId") long farmId,
                                                           @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                           @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Product> products = productService.findByFarmId(farmId, new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/farms/{farmId}/products/{productId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing product", notes = "Returns the updated product.")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "userId") long userId,
                                                 @PathVariable(value = "farmId") long farmId,
                                                 @PathVariable(value = "productId") long productId,
                                                 @RequestBody Product product) {
        productService.update(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userId}/farms/{farmId}/products/{productId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete the Product with the given id.")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "userId") long userId,
                                                 @PathVariable(value = "farmId") long farmId,
                                                 @PathVariable(value = "productId") long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
