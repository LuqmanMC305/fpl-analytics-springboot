# ⚽ FPL Analytics

A Spring Boot web application for exploring Fantasy Premier League (FPL) player statistics and analytics.

This project is being developed as a hands-on backend engineering portfolio project to learn Java development , Spring Boot architecture, external API integration, database persistence, and full-stack web development.

---

## 🚀 Project Goals

- Search for Premier League players
- Display real FPL statistics
- Learn Spring Boot best practices
- Integrate external REST APIs
- Store and analyse player data using PostgreSQL
- Build a foundation for future FPL strategy and draft-planning tools

---

## ✨ Current Features

### ✅ Implemented

- Spring Boot application setup
- Thymeleaf server-side rendering
- Tailwind CSS UI
- External API integration with Official FPL API
- JSON deserialisation using DTOs
- Player search by name
- Dynamic player details page
- PostgreSQL integration

### 🚧 In Progress

- Improved player statistics dashboard
- Error handling and validation
- Logging
- Database persistence layer (JPA)

### 📋 Planned

- Save favourite players
- Team and club information
- Historical player analytics
- Fixture difficulty analysis
- Predicted points models
- Draft builder
- REST API endpoints
- React frontend
- User accounts and authentication

---

## 🏗️ Architecture

```text
Browser
    │
    ▼
Thymeleaf Views
    │
    ▼
PlayerController
    │
    ▼
PlayerService
    │
    ▼
FplApiClient
    │
    ▼
Official FPL API
```

Future architecture:

```text
Browser / React
        │
        ▼
 Spring Boot API
        │
        ▼
 Business Services
        │
 ┌──────┴──────┐
 ▼             ▼
PostgreSQL   FPL API
```

---

## 🛠️ Tech Stack

### Backend

- Java 25
- Spring Boot
- Spring MVC
- Lombok

### Frontend

- Thymeleaf
- Tailwind CSS

### Database

- PostgreSQL

### Build Tool

- Maven

---

## 📂 Project Structure

```
src/main/java/com/luqman/fpl_analytics
├── FplAnalyticsApplication
├── home
│   └── HomeController
├── player
│   ├── PlayerController
│   ├── PlayerService
│   ├── PlayerRepository
│   ├── Player
│   └── dto
│       └── PlayerSearchRequest
├── external
│   ├── FplApiClient
│   └── fpl
│       ├── client
│       └── dto
│           ├── BootstrapResponse
│           └── PlayerDto
├── resources
│   ├── application.yaml
│   ├── static
│   └── templates
│       ├── index.html
│       └── player
│           └── details.html
└── test
    └── java/com/luqman/fpl_analytics
        └── FplAnalyticsApplicationTests
```

---

## 🌐 External Data Source

Player data is retrieved from the official Fantasy Premier League API:

https://fantasy.premierleague.com/api/bootstrap-static/

Examples of available data:

- Player names
- Prices
- Total points
- Goals scored
- Assists
- Minutes played
- Ownership percentage
- Form statistics

---

## 🎯 Learning Objectives

This project is being used to practise:

- Dependency Injection
- MVC Architecture
- DTO Design
- REST API Consumption
- JSON Deserialisation
- Validation
- Exception Handling
- Logging
- JPA & Hibernate
- Database Migrations
- Testing
- RESTful API Design

---

## 🚦 Getting Started

### Clone the repository

```bash
git clone <repository-url>
```

### Run PostgreSQL

Ensure PostgreSQL is running locally.

### Configure application.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fpl_analytics
    username: postgres
    password: your_password
```

### Start the application

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## 📝 Status

🚧 Work In Progress
