package com.example.order.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.common.exception.CustomException.OrderServiceException;
import com.example.common.model.MenuItemDto;
import com.example.common.model.UserDto;
import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.repository.OrderRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	@Test
	void calculateTotalBillLogic_FirstTimeUserWithFoodItem_ShouldApplyDiscount() {
		// Arrange
		UserDto user = new UserDto(1L, "John Doe", "a", "aa");
		MenuItemDto foodItem = new MenuItemDto(1L, "Burger", "Food", 10.0);
		int quantity = 2;

		// Mock the orderRepository behavior
		when(orderRepository.findByUserId(user.getId())).thenReturn(new ArrayList<>());

		// Act
		double totalBill = orderService.calculateTotalBillLogic(user, foodItem, quantity);

		// Assert
		assertEquals(4.0, totalBill);
	}

	@Test
	void calculateTotalBillLogic_ReturningUserWithFoodItem_ShouldApplyDiscount() {
		// Arrange
		UserDto user = new UserDto(1L, "John Doe", "aa", "qq");
		MenuItemDto foodItem = new MenuItemDto(1L, "Pizza", "Food", 15.0);
		int quantity = 3;

		// Mock the orderRepository behavior
		when(orderRepository.findByUserId(user.getId())).thenReturn(createMockOrders());

		// Act
		double totalBill = orderService.calculateTotalBillLogic(user, foodItem, quantity);

		// Assert
		assertEquals(6.0, totalBill); // 3 pizzas * $15 each - 20% discount
	}

	@Test
	void calculateTotalBillLogic_ReturningUserWithNonFoodItem_ShouldNotApplyDiscount() {
		// Arrange
		UserDto user = new UserDto(1L, "John Doe", "aa", "qq");
		MenuItemDto nonFoodItem = new MenuItemDto(1L, "Beer", "Alcoholic", 5.0);
		int quantity = 1;

		// Mock the orderRepository behavior
		when(orderRepository.findByUserId(user.getId())).thenReturn(createMockOrders());

		// Act
		double totalBill = orderService.calculateTotalBillLogic(user, nonFoodItem, quantity);

		assertEquals(5.0, totalBill); // 1 beer * $5 each - no discount
	}

	@Test
	void calculateTotalBillLogic_ErrorFetchingOrders_ShouldThrowOrderServiceException() {
		// Arrange
		UserDto user = new UserDto(1L, "John Doe", "aa", "qq");
		MenuItemDto foodItem = new MenuItemDto(1L, "Burger", "Food", 10.0);
		int quantity = 2;

		// Mock the orderRepository behavior to throw an exception
		when(orderRepository.findByUserId(user.getId())).thenThrow(new RuntimeException("Database connection error"));

		// Act and Assert
		assertThrows(OrderServiceException.class, () -> orderService.calculateTotalBillLogic(user, foodItem, quantity));
	}

	private List<Order> createMockOrders() {
		// Mocked list of orders for a returning user
		List<Order> orders = new ArrayList<>();

		// Creating order items for the first order
		List<OrderItem> orderItems1 = new ArrayList<>();
		orderItems1.add(new OrderItem(1L, null, 1L, 2));
		orders.add(new Order(1L, 1L, orderItems1));

		// Creating order items for the second order
		List<OrderItem> orderItems2 = new ArrayList<>();
		orderItems2.add(new OrderItem(2L, null, 2L, 1));
		orders.add(new Order(2L, 1L, orderItems2));

		return orders;
	}

}
