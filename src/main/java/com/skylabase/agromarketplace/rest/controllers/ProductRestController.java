package com.skylabase.agromarketplace.rest.controllers;

import com.skylabase.agromarketplace.model.Product;
import com.skylabase.agromarketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.naming.OperationNotSupportedException;

@RestController
@RequestMapping(value = "/api/v1/products")
@Api(value = "Product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get products", notes = "Returns a list of all products.")
    public ResponseEntity<Page<Product>> getProducts(Pageable pageable) {
        final Page<Product> products = productService.listAllByPage(pageable);
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get product", notes = "Returns a product.")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Product product) {
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
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Product existing, @RequestBody Product product)
            throws  Exception {
       throw new OperationNotSupportedException("");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Product product) {
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}