
package com.example.order.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.common.model.UserDto;

@FeignClient(name = "user-service") // Name of the micro-service
public interface UserServiceFeignClient {

	@GetMapping("/users/username/{username}")
	UserDto getUserByUsername(@PathVariable String username);
}
