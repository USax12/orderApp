package com.example.order.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.repository.OrderItemRepository;
import com.example.order.repository.OrderRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		seedData();
	}

	private void seedData() {
		// Create and save orders with order items
		Order order1 = new Order();
		order1.setId(1L);
		order1.setUserId(1L);
		order1.setOrderItems(null);
		orderRepository.save(order1);

		Order order2 = new Order();
		order2.setId(2L);
		order2.setUserId(1L);
		order2.setOrderItems(null);
		orderRepository.save(order2);

		// Create and save order items
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setOrder(order1);
		orderItem1.setMenuItem(1L); // Set the appropriate menu item ID
		orderItem1.setQuantity(2);
		orderItemRepository.save(orderItem1);

		OrderItem orderItem2 = new OrderItem();
		orderItem2.setOrder(order1);
		orderItem2.setMenuItem(2L); // Set the appropriate menu item ID
		orderItem2.setQuantity(1);
		orderItemRepository.save(orderItem2);

		OrderItem orderItem3 = new OrderItem();
		orderItem3.setOrder(order2);
		orderItem3.setMenuItem(3L); // Set the appropriate menu item ID
		orderItem3.setQuantity(3);
		orderItemRepository.save(orderItem3);

		// Add more orders and order items as needed
	}

}
