package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Order;
import com.skylabase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Creates a new element of type Order in the system
     *
     * @param order an order of the element been created
     * @return the created element
     */
    @Override
    public Order create(Order order) {
        if (exists(order.getId())) {
            throw new ItemAlreadyExistsException("cannot create Order which already exists");
        }
        return orderRepository.save(order);
    }

    /**
     * Get an element of type Order with given id.
     *
     * @param id the id of the element to get
     * @return the element if found
     */
    @Override
    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Page<Order> findByBuyerId(long buyerId, Pageable pageable) {
        return orderRepository.findByBuyerId(buyerId, pageable);
    }

    /**
     * Get all elements of type Order in the system.
     *
     * @return list of all elements found
     * @param pageable
     */
    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    /**
     * Update an existing element in the system.
     *
     * @param order the updated version of the existing element
     * @return the updated version of the existing element
     */
    @Override
    public Order update(Order order) {
        if (!exists(order.getId())) {
            throw new ItemNotFoundException("cannot update Order that does not exist");
        }
        return orderRepository.save(order);
    }

    /**
     * Deletes and element of type Order from the system.
     *
     * @param orderId the element been deleted
     */
    @Override
    public void delete(long orderId) {
        orderRepository.delete(orderId);
    }

    /**
     * Checks if element exists in the system.
     *
     * @param orderId the element
     * @return true if element exists else return false
     */
    @Override
    public boolean exists(long orderId) {
        return orderRepository.exists(orderId);
    }
}

interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    Order findById(@Param("id") long id);

    Page<Order> findByBuyerId(@Param("user_id") long buyerId, Pageable pageable);
}