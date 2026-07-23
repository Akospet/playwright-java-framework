# Enterprise Playwright Test Automation Framework

![Java](https://img.shields.io/badge/Java-21-blue)
![Playwright](https://img.shields.io/badge/Playwright-Latest-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-orange)
![GitHub Actions](https://img.shields.io/badge/GitHub-Actions-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

> Enterprise-grade UI Test Automation Framework built with **Java 21**, **Playwright**, **JUnit 5** and **GitHub Actions**.

This project demonstrates modern enterprise UI test automation architecture and best practices.

The framework includes:

- Enterprise Page Object Model (POM)
- Thread-safe Playwright Factory
- Persistent Authentication (Storage State)
- Visual Regression Testing
- API Mocking & Network Interception
- Screenshot on Failure
- Video Recording
- Trace Viewer
- Embedded HTTP Test Server
- GitHub Actions CI/CD

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
- Persistent authenticated browser sessions (Playwright Storage State)
- Visual Regression Testing
- Automatic Baseline Image Creation
- Difference Image Generation

## Continuous Integration

- GitHub Actions
- Automated Playwright Browser Installation
- Maven Build
- Automatic Test Execution
- Screenshots
- Videos
- Trace Files

---

# Technology Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Playwright | Latest |
| JUnit | 5 |
| AssertJ | Latest |
| image-comparison | 4.4 |
| Maven | Latest |
| SLF4J + Logback | Latest |
| GitHub Actions | CI/CD |

---

# Framework Architecture

```text
                       Tests
                         │
                         ▼
                    BaseTest
                         │
                         ▼
              Playwright Factory
                         │
          ┌──────────────┴──────────────┐
          │                             │
          ▼                             ▼
   Browser Context               Storage State
          │
          ▼
      Page Objects
          │
          ▼
        Utilities
          │
 ┌────────┼──────────┬──────────────┬───────────────┐
 ▼        ▼          ▼              ▼
Video   Trace   Screenshot   Visual Comparator
```

---

# Storage State Authentication

The framework supports **Playwright Storage State** to reuse authenticated browser sessions.

Authentication is performed only once and persisted as:

```text
src/test/resources/auth/storageState.json
```

Subsequent tests automatically reuse the authenticated browser context.

Benefits:

- Faster execution
- Reduced login overhead
- Enterprise-style authenticated testing

---

# Visual Regression Testing

The framework supports automated visual regression testing using the **image-comparison** library.

Features:

- Automatic baseline creation
- Pixel-by-pixel comparison
- Difference image generation
- CI-friendly output

Project structure:

```text
src/test/resources/
└── visual/
    └── baseline/

target/
└── visual/
    ├── actual/
    └── diff/
```

Example:

```java
VisualComparator.compare(
        page.locator(".inventory_list"),
        "inventory-list");
```

When differences are detected the framework automatically generates:

- Baseline Image
- Actual Image
- Difference Image

and fails the test with detailed file locations.

---

# API Mocking Demo

The framework contains a lightweight demo application running on an **embedded HTTP server**.

```text
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

This allows API mocking examples to run consistently on:

- macOS
- Windows
- Linux
- GitHub Actions
- CI/CD environments

without relying on external services.

---

# Project Structure

```text
src
├── main
│
└── test
    ├── java
    │   ├── base
    │   ├── factory
    │   ├── listeners
    │   ├── pages
    │   ├── server
    │   ├── utils
    │   ├── visual
    │   └── tests
    │
    └── resources
        ├── auth
        ├── demo
        ├── mock
        └── visual
            └── baseline

target
└── visual
    ├── actual
    └── diff
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
- Thread-safe Playwright Factory
- Configuration Management
- Storage State Authentication
- Visual Regression Testing
- Screenshot on Failure
- Video Recording
- Trace Viewer
- API Mocking
- Network Interception
- Embedded HTTP Test Server
- DOM Validation
- GitHub Actions CI/CD

---

# Roadmap

- Parallel Execution
- Cross Browser Matrix
- Allure Reporting
- Docker Execution
- BrowserStack Integration
- HTML Reporting
- Visual Regression Artifacts in GitHub Actions

---

# Why Playwright?

Compared to traditional browser automation frameworks, Playwright provides:

- Auto Waiting
- Reliable Locators
- Cross-browser support
- Built-in Network Interception
- API Mocking
- Trace Viewer
- Video Recording
- Fast Execution
- Stable Parallel Execution
- Modern Browser Engine Support

---

# Enterprise Highlights

This project demonstrates several enterprise-grade automation practices:

- Clean layered architecture
- Thread-safe browser management
- Reusable Page Objects
- Configuration management
- Persistent authenticated sessions
- Visual regression testing
- API mocking
- Automatic screenshots, videos and traces
- CI/CD ready
- Cross-platform execution (Windows, Linux, macOS)

---

# Author

**Ákos Péteri**

Senior Java Developer • Test Automation Engineer

Enterprise UI Test Automation • Java • Playwright • JUnit • CI/CD
