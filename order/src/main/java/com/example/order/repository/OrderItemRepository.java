package com.example.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {

}
