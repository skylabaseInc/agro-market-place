package com.skylabase.rest.controllers;

import com.skylabase.model.Product;
import com.skylabase.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@Api(value = "Product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get products", notes = "Returns a list of all products.")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> returnValue = productService.findAll();
        if (returnValue == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get product", notes = "Returns a product.")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new product", notes = "Returns the created product.")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (productService.exists(product)) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }
        final Product created = productService.create(product);
        return new ResponseEntity<Product>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing product", notes = "Returns the updated product.")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        final Product existing = productService.findById(id);
        if (existing == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        existing.setName(product.getName());

        productService.update(existing);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a product")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        final Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productService.delete(product);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete all products")
    public ResponseEntity<Product> deleteAllProducts() {
        productService.deleteAll();
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
}
