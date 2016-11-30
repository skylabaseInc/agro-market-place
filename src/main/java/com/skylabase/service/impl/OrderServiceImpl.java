package com.skylabase.service.impl;

import com.skylabase.model.Order;
import com.skylabase.service.OrderService;
import com.skylabase.service.ProductService;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private boolean buyerIdExists(String buyerId) {
        return userService.findById(buyerId) != null;
    }

    private boolean productIdExists(String productId) {
        return productService.findById(productId) != null;
    }

    /**
     * Get an element of type Order with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Order findById(String id) {
        return orderRepository.findOne(id);
    }

    /**
     * Get all elements of type Order in the system.
     *
     * @return list of all elements found
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * Creates a new element of type Order in the system
     *
     * @param instance an instance of the element been created
     * @return the created element
     */
    @Override
    public Order create(Order instance) {
        if (exists(instance)) {
            // should throw exception: Already Exists
            return null;
        }
        if (!buyerIdExists(instance.getBuyerId())) {
            // should throw exception: No such Buyer
            return null;
        }
        if (!productIdExists(instance.getProductId())) {
            // should throw exception: No such product
            return null;
        }
        return orderRepository.save(instance);
    }

    /**
     * Update an existing element in the system.
     *
     * @param instance the updated version of the existing element
     * @return the updated version of the existing element
     */
    @Override
    public Order update(Order instance) {
        if (!buyerIdExists(instance.getBuyerId())) {
            // should throw exception: No such Buyer
            return null;
        }
        if (!productIdExists(instance.getProductId())) {
            // should throw exception: No such product
            return null;
        }
        return orderRepository.save(instance);
    }

    /**
     * Deletes and element of type Order from the system.
     *
     * @param instance the element been deleted
     */
    @Override
    public void delete(Order instance) {
        orderRepository.delete(instance);
    }

    /**
     * Checks if element exists in the system.
     *
     * @param instance the element
     * @return true if element exists else return false
     */
    @Override
    public boolean exists(Order instance) {
        if (instance.getId() == null) {
            return false;
        }
        return orderRepository.exists(instance.getId());
    }

    /**
     * Delete all Orders from the system.
     */
    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }
}

interface OrderRepository extends MongoRepository<Order, String> {
}