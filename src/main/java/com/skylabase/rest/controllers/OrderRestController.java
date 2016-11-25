package com.skylabase.rest.controllers;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skylabase.model.Order;
import com.skylabase.service.OrderService;

/**
 * RestController that handles request for {@link Order} objects
 *
 * @see Order
 * @see OrderService
 *
 * @author faison
 */
@RestController
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    /**
     * Get a list of all orders in the system.
     *
     * @return the list of orders
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.findAll();

        if (orders.isEmpty()) {
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    /**
     * Get order with given order id.
     *
     * @param id
     *            the id of the order to return
     * @return the order of given id
     *
     * @see OrderService#findById(String)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    /**
     * Create a new order in the system.
     *
     * @param order
     *            the order to be created
     * @param order
     * @return an HttpStatus.CREATED if order was successfully created
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (orderService.exists(order)) {
            return new ResponseEntity<Order>(HttpStatus.CONFLICT);
        }

        orderService.create(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<Order>(headers, HttpStatus.CREATED);
    }

    /**
     * Updates an existing order.
     *
     * @param id
     *            the id of the order been updated
     * @param updated
     *            an updated instance to persist
     * @return the updated order or an HttpStatus.NOT_FOUND if the order been
     *         updated does not exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String id, @RequestBody Order updated) {
        Order currentOrder = orderService.findById(id);

        if (currentOrder == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        currentOrder.setBuyer_id(updated.getBuyer_id());
        currentOrder.setProduct_id(updated.getProduct_id());
        currentOrder.setQuantity(updated.getQuantity());
        currentOrder.setDate(updated.getDate());
        orderService.update(currentOrder);
        return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
    }

    /**
     * Deletes a order from the system.
     *
     * @param id
     *            the id of the order been deleted
     * @return if order was not found an HttpStatus.NOT_FOUND is returned else an
     *         HttpStatus.NO_CONTENT is returned
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") String id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        orderService.delete(order);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes all orders from the system
     *
     * @return an HttpStatus.NO_CONTENT
     *
     * @see OrderService#deleteAll()
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteAllOrders() {
        orderService.deleteAll();
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}
