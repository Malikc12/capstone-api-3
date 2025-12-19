EasyShop E-Commerce API
A full-featured e-commerce REST API built with Spring Boot that powers an online store. This capstone project implements product management, shopping cart functionality, user authentication, and profile management.


# Capstone API 3

Capstone API 3 is a Java Spring Boot REST API built as part of a backend capstone project.  
The application follows a layered architecture using Controllers, DAOs, and Models, and connects to a MySQL database to manage data through RESTful endpoints.



## Getting Started

### Prerequisites
- Java JDK installed
- MySQL installed and running
- Git (optional)

---
Phase 1: Categories Controller ✅

Implemented REST endpoints for category management
CRUD operations: GET, POST, PUT, DELETE
Categories dropdown working in web application
Admin-only access for create, update, and delete operations
All endpoints tested in Insomnia

Phase 2: Bug Fixes ✅
Bug 1: Product Search Filters

Fixed search by minimum and maximum price
Verified working in both web application and Insomnia
Proper query parameter handling for category, price range, and color filters

Bug 2: Duplicate Products

Fixed UPDATE operation creating duplicate products
Modified SQL query to properly update existing products
Verified only one product exists after update in Insomnia testing

Phase 3: Shopping Cart (Optional) ✅

View cart with all items and total price
Add products to cart
Add same product twice (automatically increases quantity)
Update item quantities
Clear entire cart
Cart persists in database between sessions
All endpoints require user authentication
Tested in both web application and Insomnia

Phase 4: User Profile (Optional) ✅

View user profile information
Update profile details (name, email, phone, address)
Profile automatically created on user registration
Secure access using Principal object for user identification
Tested in both web application and Insomnia
