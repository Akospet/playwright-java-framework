# Enterprise Playwright Test Automation Framework

Enterprise-grade Playwright Test Automation Framework built with **Java 21**, **Playwright** and **JUnit 5**.

This project demonstrates modern UI test automation techniques commonly used in enterprise environments, including Page Object Model, API Mocking, Network Interception, Trace Viewer, Screenshot & Video Recording.

---

## Technology Stack

- Java 21
- Playwright
- JUnit 5
- AssertJ
- Maven
- SLF4J + Logback
- GitHub Actions

---

## Features

### Core Framework

- Configuration Management
- Browser Factory
- Thread-safe Playwright Factory
- Page Object Model (POM)
- JUnit 5 Test Structure

### Test Automation

- UI Testing
- Screenshot on Failure
- Video Recording
- Trace Viewer
- API Mocking
- Network Interception

### Demo Application

A small HTML demo application is included to demonstrate Playwright API interception without depending on external websites.

Workflow:

```
HTML Page
      │
      ▼
fetch("/api/fruits")
      │
      ▼
Playwright Route Interception
      │
      ▼
Mock JSON Response
      │
      ▼
DOM Rendering
      │
      ▼
Assertions
```

---

## Project Structure

```
src
├── main
│   ├── java
│   └── resources
│
└── test
    ├── java
    │   ├── base
    │   ├── pages
    │   ├── listeners
    │   └── tests
    │
    └── resources
        ├── demo
        └── mock
```

---

## Running Tests

Run all tests:

```bash
mvn clean test
```

Run a single test:

```bash
mvn -Dtest=LoginTest test
```

---

## Current Demonstrations

- Login automation
- Page Object Model
- API Mocking
- Network Interception
- DOM validation
- Screenshot on failure
- Video recording
- Trace Viewer

---

## Planned Improvements

- Storage State (Authenticated Sessions)
- Parallel Execution
- Cross Browser Matrix
- Allure Reporting
- Docker Execution
- CI Improvements

---

## Why Playwright?

Playwright provides powerful capabilities beyond traditional browser automation:

- Auto-waiting
- Network interception
- API mocking
- Trace Viewer
- Video recording
- Cross-browser support
- Fast and reliable execution

---

## Author

**Ákos Péteri**

Senior Java Developer • Test Automation Engineer

GitHub Portfolio Project
