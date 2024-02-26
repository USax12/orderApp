package com.example.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.exception.CustomException.OrderServiceException;
import com.example.common.model.MenuItemDto;
import com.example.common.model.UserDto;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public double calculateTotalBillLogic(UserDto user, MenuItemDto menuItems, int qty) {

		try {
			// Check if any order exists for the user
			boolean isFirstOrder = isFirstOrderForUser(user.getId());

			// Apply discount based on the promo code
			double discount = 0.0;
			if (isFirstOrder) {
				discount = applyFirstTimeUserDiscount(menuItems, qty);
			} else {
				discount = applyReturningUserDiscount(menuItems, qty);
			}

			// Calculate the total bill after applying the discount
			double totalBill = calculateTotalBillWithoutDiscount(menuItems);
			totalBill = (totalBill - discount); // Deduct the discount
			return totalBill;
		} catch (Exception e) {
			throw new OrderServiceException("Error calculating total bill", e);

		}
	}

	private boolean isFirstOrderForUser(Long userId) {

		try {
			// If the list is empty, it's the user's first order
			List<Order> userOrders = orderRepository.findByUserId(userId);
			return userOrders.isEmpty();
		} catch (Exception e) {
			throw new OrderServiceException("Error checking if it's the first order for the user", e);
		}
	}

	private double applyFirstTimeUserDiscount(MenuItemDto menuItem, int qty) {

		try {
			// Apply 30% discount on food and 10% discount on liquor for first-time users
			double foodDiscount = 0.3;
			double liquorDiscount = 0.1;

			// Calculate the total discount based on the provided item
			if ("Food".equals(menuItem.getCategory())) {
				return menuItem.getPrice() * qty * foodDiscount;
			} else if ("Alcoholic".equals(menuItem.getCategory())) {
				return menuItem.getPrice() * qty * liquorDiscount;
			} else {
				return 0.0;
			}
		} catch (Exception e) {
			throw new OrderServiceException("Error applying first-time user discount", e);
		}
	}

	private double applyReturningUserDiscount(MenuItemDto menuItem, int qty) {
		try {
			// Apply 20% discount on food items for returning users
			double foodDiscount = 0.2;

			// Calculate the total discount based on food items
			if ("Food".equals(menuItem.getCategory())) {
				return menuItem.getPrice() * qty * foodDiscount;
			} else {
				// If the item is not a food item, no discount is applied
				return 0.0;
			}
		} catch (Exception e) {
			throw new OrderServiceException("Error applying returning user discount", e);
		}
	}

	private double calculateTotalBillWithoutDiscount(MenuItemDto menuItem) {
		return menuItem.getPrice();
	}
}
