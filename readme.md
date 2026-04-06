# 🛠️ E-Commerce Backend (Spring Boot)

A scalable backend system for an e-commerce platform built using Spring Boot, focusing on authentication, API design, and robust data handling.

🔗 **Frontend Repo:** https://github.com/Priynshu2341/ecommerceFrontend  
🔗 **Live Frontend:** https://whimsical-valkyrie-77e952.netlify.app/

---

## 🚀 Features

- 🔐 JWT Authentication (Access + Refresh Token)
- 🔁 Secure token refresh flow
- 🛍️ Product management with pagination
- 🔍 Search functionality using custom queries
- 🛒 Cart and order management APIs
- ⚡ Optimized database queries with JPA
- ❗ Global exception handling
- 📊 Structured API responses

---

## 🧠 Tech Stack

- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **PostgreSql**
- **Gradle**
- **DTOs**

---

## ⚙️ Architecture Overview

- Layered architecture:
    - Controller → Service → Repository
- DTO-based data transfer
- Centralized exception handling
- Clean separation of concerns

---

## 🔐 Authentication Flow

- User logs in → receives:
    - Access Token (short-lived)
    - Refresh Token (long-lived)
- Access token used for API requests
- On expiration:
    - Refresh token endpoint generates new access token
- Secured endpoints protected via Spring Security

---

## 📡 API Endpoints (Sample)

### Auth
- `POST /auth/login`
- `POST /auth/refresh`

### Products
- `GET /products?page=0&size=10`
- `GET /products/search?query=...`

### Cart
- `POST /cart/add`
- `GET /cart`

---

## 📦 Installation & Setup

```bash
git clone https://github.com/Priynshu2341/Ecommerce-Backend.git
cd Ecommerce-Backend