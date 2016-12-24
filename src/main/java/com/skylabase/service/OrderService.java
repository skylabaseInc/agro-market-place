package com.skylabase.service;

import com.skylabase.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order create(Order order);

    Order findById(long id);

    Page<Order> findByBuyerId(long buyerId, Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    Order update(Order order);

    void delete(long orderId);

    boolean exists(long orderId);
}
