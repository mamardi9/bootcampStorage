# Bootcamp Storage Application

## Description

This project is a backend application developed in Java using Spring Boot and Maven. It is composed of several layers:

- **Entity Layer**: Defines the data models and uses JPA for persistence in an in-memory H2 database.
- **Repository Layer**: Manages CRUD operations through interfaces extending `JpaRepository`.
- **Service Layer**: Implements business logic and acts as an intermediary between controllers and repositories.
- **Controller Layer**: Handles views and redirections in a web application based on Spring MVC.

Additionally, the application includes:
- User authentication and authorization using Spring Security.
- Document management functionality.
- Maven for dependency management and project building.

## Features

- CRUD operations for entities.
- Secure user authentication and authorization.
- Document storage and management.
- In-memory database for testing and development.

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Security**
- **Spring MVC**
- **JPA**
- **H2 Database**
- **Maven**
