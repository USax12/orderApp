package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.model.MenuItemDto;
import com.example.common.model.UserDto;
import com.example.order.service.OrderService;
import com.example.order.utility.MenuServiceFeignClient;
import com.example.order.utility.UserServiceFeignClient;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private UserServiceFeignClient userService;

	@Autowired
	private MenuServiceFeignClient menuService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/calculateTotalBill")
	public ResponseEntity<String> calculateTotalBill(@RequestParam String item, @RequestParam String username,
			@RequestParam int quantity) {

		// Fetch user information from User Microservice
		UserDto user = userService.getUserByUsername(username);

		// Fetch menu items from Menu Microservice
		MenuItemDto menuItems = menuService.getMenuItemByName(item);

		// Logic to calculate total bill based on user and menu information
		double totalBill = orderService.calculateTotalBillLogic(user, menuItems, quantity);

		String responseMessage = String.format("Bill amount for %d %s: $%.2f", quantity, menuItems.getName(),
				totalBill);
		return ResponseEntity.ok(responseMessage);
	}
}
