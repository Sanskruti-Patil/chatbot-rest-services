package com.rest.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.exception.RecordNotFoundException;
import com.rest.api.model.OrderEntity;
import com.rest.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;

	public List<OrderEntity> getAllOrder() {
		List<OrderEntity> orderList = repository.findAll();

		if (orderList.size() > 0) {
			return orderList;
		} else {
			return new ArrayList<OrderEntity>();
		}
	}

	public OrderEntity getOrderById(Long id) throws RecordNotFoundException {
		Optional<OrderEntity> shop = repository.findById(id);

		if (shop.isPresent()) {
			return shop.get();
		} else {
			throw new RecordNotFoundException("No order record exist for given id");
		}
	}

	public OrderEntity createOrUpdateOrder(OrderEntity entity) throws RecordNotFoundException {
		Optional<OrderEntity> shop = repository.findById(entity.getId());

		if (shop.isPresent()) {
			OrderEntity newEntity = shop.get();
			newEntity.setItem(entity.getItem());

			newEntity = repository.save(newEntity);

			return newEntity;
		} else {
			entity = repository.save(entity);

			return entity;
		}
	}

	public void deleteOrderById(Long id) throws RecordNotFoundException {
		Optional<OrderEntity> shop = repository.findById(id);

		if (shop.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No order record exist for given id");
		}
	}
}