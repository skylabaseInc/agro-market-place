package com.skylabase.agromarketplace.service.impl;

import com.skylabase.agromarketplace.model.Order;
import com.skylabase.agromarketplace.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Get an element of type Order with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Order findById(Long id) {
        return orderRepository.findOne(id);
    }

    /**
     * Get all elements of type Order in the system.
     *
     * @return list of all elements found
     */
    @Override
    public List<Order> findAll() {
        final List<Order> orders = new ArrayList<>();
        for (Order order: orderRepository.findAll()) {
            orders.add(order);
        }
        return orders;
    }

    /**
     * Creates a new element of type Order in the system
     *
     * @param instance an instance of the element been created
     * @return the created element
     */
    @Override
    public Order create(Order instance) {
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
        return orderRepository.exists(instance.getId());
    }
}

interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}