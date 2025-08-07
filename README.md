# SpringHotelManager

SpringHotelManager is a web application for managing hotel reservations, rooms, customers, and user accounts, built with Spring Boot.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/ictdemy/springHotelManager/
│   │   ├── configuration/            # Security configuration
│   │   ├── controllers/              # Web controllers (MVC)
│   │   ├── data/
│   │   │   ├── entities/             # JPA entities
│   │   │   ├── initializers/         # Database initializer
│   │   │   └── repositories/         # Spring Data repositories
│   │   ├── models/
│   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   ├── mappers/              # Entity-DTO mappers
│   │   │   ├── enums/                # Enums (e.g., user roles)
│   │   │   ├── exceptions/           # Custom exceptions
│   │   │   └── services/             # Service layer
│   │   └── SpringHotelManagerApplication.java
│   ├── resources/
│   │   ├── static/                   # Static files (CSS, JS, images)
│   │   └── templates/                # Thymeleaf templates
│   │       ├── fragments/           # Layouts, headers, etc.
│   │       └── pages/               # Pages grouped by functionality
├── test/                             # Unit tests
```

---

## Getting Started

### Requirements
- Java 17+
- Maven 3.8+
- IntelliJ IDEA or similar IDE

### Running the App
```bash
# Run using Maven
mvn spring-boot:run
```

Application will be available at: [http://localhost:8080]

---

## Technologies Used

- **Spring Boot**
- **Spring MVC**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **Lombok**
- **MapStruct**
- **H2 Database** (or other RDBMS)
- **HTML/CSS/JavaScript**

---

## Features

- User registration and login
- Role-based access control (e.g., `ADMIN`, `USER`)
- Customer management
- Room management
- Assign rooms to customers
- Room availability checks
- Payment account management
- Frontend rendered via Thymeleaf

---

## Planned Features

The following features are planned for future development:

- **Payment Gateway Integration**
  - Implement secure integration with a third-party payment provider (e.g., Stripe, PayPal)
  - Allow online payment for room reservations and bar charges

- **Storing History of Past Customers**
  - Save information about customers who have checked out
  - Enable admins to view history, stays, and payments

- **Returning Customer Discounts**
  - Automatically apply discounts for returning guests
  - Track customer history to determine eligibility

- **Bar and Bartender Module**
  - Add support for a hotel bar including:
    - List of available products (e.g., drinks, snacks)
    - Bartender user role with specific permissions
    - Bar-specific UI for adding orders

- **Bar Accounts for Customers**
  - Assign bar accounts to currently accommodated customers
  - Enable adding bar items to customer's bill
  - Link bar charges with final checkout/invoice

- **Bar Product Management**
  - CRUD operations for bar items (add, edit, delete products)
  - Assign prices, categories (e.g., alcoholic, non-alcoholic)


---

## Testing

Unit tests are located in:
```
src/test/java/com/ictdemy/springHotelManager/
```

---

## Previews

Here are some screenshots of the application:

![Login](static/images/screenshots/Screenshot 2025-08-07 091501.png)

---

## Author

- Name: Tomas Hajas 
- GitHub: https://github.com/Thomas-363
