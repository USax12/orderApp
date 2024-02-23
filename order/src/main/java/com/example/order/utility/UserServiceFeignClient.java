package com.example.order.utility;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")  // Name of the microservice to communicate with
public interface UserServiceFeignClient {

    @GetMapping("/users/{userId}")
    User getUserById(@PathVariable Long userId);
}
