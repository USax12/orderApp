package com.example.order.service;

import com.example.common.model.MenuItemDto;
import com.example.common.model.UserDto;

public interface OrderService {

	double calculateTotalBillLogic(UserDto user, MenuItemDto menuItems, int qty);

}
