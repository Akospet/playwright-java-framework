# Enterprise Playwright Test Automation Framework

![Java](https://img.shields.io/badge/Java-21-blue)
![Playwright](https://img.shields.io/badge/Playwright-Latest-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

> Enterprise-grade UI Test Automation Framework built with **Java 21**, **Playwright**, **JUnit 5** and **GitHub Actions**.

This project demonstrates modern enterprise test automation techniques including **Page Object Model**, **API Mocking**, **Network Interception**, **Trace Viewer**, **Screenshot on Failure**, **Video Recording** and **Continuous Integration**.

---

# Features

## Enterprise Framework

- Configuration Management
- Browser Factory
- Thread-safe Playwright Factory
- Page Object Model (POM)
- JUnit 5 Test Architecture
- Logging (SLF4J + Logback)

## Test Automation

- UI Testing
- Screenshot on Failure
- Video Recording
- Trace Viewer
- API Mocking
- Network Interception
- Embedded HTTP Test Server
- Persistent authenticated browser sessions using Playwright Storage State

## Continuous Integration

- GitHub Actions
- Automated Playwright Browser Installation
- Maven Build
- Automatic Test Execution
- Test Artifacts (Screenshots, Videos, Traces)

---

# Technology Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Playwright | Latest |
| JUnit | 5 |
| AssertJ | Latest |
| Maven | Latest |
| SLF4J + Logback | Latest |
| GitHub Actions | CI/CD |

---

# Architecture

```
                Test
                  │
                  ▼
          Playwright Factory
                  │
                  ▼
            Browser Context
                  │
                  ▼
             Page Objects
                  │
                  ▼
              Application
```

---

# API Mocking Demo

The framework contains a small demo application running on an **embedded HTTP server**.

```
Browser
      │
      ▼
http://localhost/index.html
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

This allows the API Mocking example to run consistently on:

- macOS
- Windows
- Linux
- GitHub Actions
- CI/CD environments

without relying on external websites.

---

# Project Structure

```
src
├── main
│   ├── java
│   └── resources
│
└── test
    ├── java
    │   ├── base
    │   ├── factory
    │   ├── listeners
    │   ├── pages
    │   ├── server
    │   └── tests
    │
    └── resources
        ├── demo
        └── mock
```

---

# Running Tests

Run all tests

```bash
mvn clean test
```

Run a single test

```bash
mvn -Dtest=LoginTest test
```

Run in headless mode

```bash
mvn clean test -Dheadless=true
```

---

# Current Demonstrations

- Login Automation
- Page Object Model
- Browser Factory
- API Mocking
- Network Interception
- Embedded HTTP Test Server
- DOM Validation
- Screenshot on Failure
- Video Recording
- Trace Viewer
- GitHub Actions CI

---

# Roadmap

- Storage State (Authenticated Sessions)
- Parallel Execution
- Cross Browser Matrix
- Allure Reporting
- Docker Execution
- BrowserStack Integration

---

# Why Playwright?

Compared to traditional browser automation frameworks, Playwright provides:

- Automatic waiting
- Built-in network interception
- API mocking
- Trace Viewer
- Video recording
- Modern browser support
- Fast execution
- Stable locators

---

# Author

**Ákos Péteri**

Senior Java Developer • Test Automation Engineer

GitHub Portfolio Project
