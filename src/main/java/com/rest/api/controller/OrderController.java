package com.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.exception.RecordNotFoundException;
import com.rest.api.model.OrderEntity;
import com.rest.api.service.OrderService;
 
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    OrderService service;
 
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrder() {
        List<OrderEntity> list = service.getAllOrder();
 
        return new ResponseEntity<List<OrderEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        OrderEntity entity = service.getOrderById(id);
 
        return new ResponseEntity<OrderEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<OrderEntity> createOrUpdateOrder(OrderEntity order)
                                                    throws RecordNotFoundException {
        OrderEntity updated = service.createOrUpdateOrder(order);
        return new ResponseEntity<OrderEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteOrderById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteOrderById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}