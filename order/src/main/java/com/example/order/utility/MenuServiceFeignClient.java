package com.example.order.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.common.model.MenuItemDto;

@FeignClient(name = "menu-service", url = "http://localhost:8081")
public interface MenuServiceFeignClient {

	@GetMapping("/menu/item/{itemName}")
	MenuItemDto getMenuItemByName(@PathVariable String itemName);
}
