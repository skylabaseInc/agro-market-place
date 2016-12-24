package com.skylabase.rest.controllers;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skylabase.model.Order;
import com.skylabase.service.OrderService;

/**
 * RestController that handles request for {@link Order} objects
 *
 * @author faison
 * @see Order
 * @see OrderService
 */
@RestController
@RequestMapping("/orders")
@Api(value = "Order")
public class OrdersRestController {

    @Autowired
    private OrderService orderService;

    /**
     * Create a new order in the system.
     *
     * @param order the order to be created
     * @param order
     * @return an HttpStatus.CREATED if order was successfully created
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new order", notes = "Returns the created order.")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order = orderService.create(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    /**
     * Get a list of all orders in the system.
     *
     * @return the list of orders
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get orders", notes = "Returns a list of all orders.")
    public ResponseEntity<Page<Order>> getOrders(@RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                 @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        final Page<Order> orders = orderService.findAll(new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Get order with given order id.
     *
     * @param id the id of the order to return
     * @return the order of given id
     * @see OrderService#findById(Long)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get order", notes = "Returns an order.")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    /**
     * Updates an existing order.
     *
     * @param orderId the orderId of the order been updated
     * @param updated an updated instance to persist
     * @return the updated order or an HttpStatus.NOT_FOUND if the order been
     * updated does not exist
     */
    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing order", notes = "Returns the updated order.")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") String orderId, @RequestBody Order updated) {
        updated = orderService.update(updated);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /**
     * Deletes a order from the system.
     *
     * @param orderId the orderId of the order been deleted
     * @return if order was not found an HttpStatus.NOT_FOUND is returned else an
     * HttpStatus.NO_CONTENT is returned
     */
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an order")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
