# Order Management System

# Overview
This system consists of three micro-services: Order Service, Menu Service, and User Service. It allows users to place orders, view menus, and manage user information.

# Micro-services

# 1. Order Service
- Manages orders and order-related functionalities.
- Communicates with the User Service to fetch user information.
- Communicates with the Menu Service to fetch menu items.

# 2. Menu Service
- Manages menu items and menu-related functionalities.
- Provides information about available menu items.

# 3. User Service
- Manages user information and user-related functionalities.
- Provides user details to other services.

# 4. Common Library
- Manages the exception handling globally 
- Provides the DTO for the Rest calls

# Usage

# 1 :Prerequisites
	-Java 11
	-Spring Boot
	-Maven
	
# 2:Getting Started:
# - Clone the Repository:  git clone
Order Repo- https://github.com/USax12/orderApp  \
Menu  Repo- https://github.com/USax12/restaurantApp  \
User  Repo- https://github.com/USax12/userApp   \
commonLibrary- https://github.com/USax12/commonLibrary  \
Eureka -https://github.com/USax12/eurekaApp   

# - Build the Project:
mvn clean install

# - Run the Application: 
mvn spring-boot:run

# - Explore APIs: 
Access the Swagger API documentation for a list of available APIs and their documentation

Menu- http://localhost:8081/swagger-ui.html   \
User- http://localhost:8081/swagger-ui.html 	\
Order- http://localhost:8083/swagger-ui.html 	\
Eureka - http://localhost:9761/		
  
Alternatively POSTMAN can be used for better representation and output.

# - Testing:
Unit tests are available in the src/test directory. Run tests using the following command: mvn test
	
	
