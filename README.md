# 🛒 Product Management Application

A full Java Console Application for managing products using MVC design pattern, JDBC, Stored Procedures, MySQL, and GitHub version control.

---

## 📚 Technologies Used
- Java 17 (or Java 8+)
- JDBC API
- MySQL 8.x
- MySQL Connector/J
- Stored Procedures
- Git + GitHub
- Console Application (no frameworks)

---

## 🏗️ Project Architecture
- **Model:** `Product.java`
- **DAO Layer:** `ProductDAO.java` (calls Stored Procedures)
- **Service Layer:** `ProductService.java` & `ProductServiceImpl.java`
- **Controller:** `MainApp.java` (console-based menu)

---

## 🚀 Features
- ➕ Add product
- 🔍 List all products
- 🔎 Find product by ID
- 🔍 Search products by name
- 🔄 Update product information
- ❌ Delete product
- 📄 Pagination - view products by page
- 💾 Add multiple products at once (with Transaction)
- ✅ Clear console user flow

---

## ⚡ Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/be-better-be-stronger/product-management.git
