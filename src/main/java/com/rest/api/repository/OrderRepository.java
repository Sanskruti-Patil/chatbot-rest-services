package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.model.OrderEntity;
 
@Repository
public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {
 
}
