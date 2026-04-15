💰 Fintech Backend System

A secure, scalable backend system built with Spring Boot that simulates real-world fintech operations such as user onboarding, authentication, and money transfers.

🚀 Overview

This project demonstrates how modern fintech systems handle:

User registration with OTP verification
Secure authentication using JWT
Transaction processing between users
Persistent data storage using JPA/Hibernate
✨ Features
🔐 OTP-based User Registration
🔑 JWT Authentication & Authorization
💸 Peer-to-peer Money Transfer
📜 Transaction History Tracking
🛡️ Spring Security Integration
⚡ RESTful API Architecture
🛠️ Tech Stack
Category	Technology
Language	Java 8+
Framework	Spring Boot
Security	Spring Security + JWT
ORM	Hibernate (JPA)
Database	H2 / MySQL
Build Tool	Maven
API Testing	Postman
📂 Project Structure
src/main/java/com/example/fintech
│
├── config/         # Security & JWT configuration
├── controller/     # REST Controllers (API endpoints)
├── service/        # Business logic layer
├── repository/     # JPA repositories
├── model/          # Entity classes
├── dto/            # Request/Response DTOs
└── FintechApplication.java
🔐 Authentication Workflow
User Registration → OTP Generation → OTP Verification → Login → JWT Token → Access Protected APIs
📌 API Endpoints
🔹 Authentication APIs
Method	Endpoint	Description
POST	/api/register	Register new user
POST	/api/verify	Verify OTP
POST	/api/login	Authenticate & get JWT
🔹 Transaction APIs
Method	Endpoint	Description
POST	/api/transfer	Transfer money
GET	/api/transactions/{userId}	Get user transactions
🔑 Authorization

All protected endpoints require a JWT token in the header:

Authorization: Bearer <JWT_TOKEN>
🧪 Sample API Request
Transfer Money
{
  "senderId": 1,
  "receiverId": 2,
  "amount": 500
}
🗄️ Database (H2 Console)
URL: http://localhost:8081/h2-console
JDBC URL:
jdbc:h2:mem:fintechdb
⚙️ Setup & Run
# Clone the repository
git clone https://github.com/Albin06/fintech-backend.git

# Navigate into project
cd fintech-backend

# Run application
mvn spring-boot:run
🧰 Testing
Use Postman to test APIs
Import the provided Postman Collection
Add JWT token in Authorization header
