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
- Self-Healing Authentication (Playwright Storage State)
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
- Self-Healing Authentication (Playwright Storage State)
- Automatic Storage State Creation
- Automatic Storage State Validation
- Automatic Session Recovery
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
 Authentication Manager         Browser Context
          │                             │
          ▼                             ▼
 Storage State Validation          Page Objects
          │
          ▼
        Utilities
          │
 ┌────────┼──────────┬──────────────┬───────────────┐
 ▼        ▼          ▼              ▼
Video   Trace   Screenshot   Visual Comparator
```

---

# Self-Healing Authentication

The framework provides **self-healing authentication** using Playwright Storage State.

Authenticated browser sessions are stored as:

```text
src/test/resources/auth/storageState.json
```

Before every authenticated test execution the framework automatically:

- Checks whether a Storage State exists
- Validates the stored authenticated session
- Automatically recreates expired sessions
- Continues test execution without manual intervention

This removes the need to execute login tests before running authenticated UI tests.

Benefits:

- Faster execution
- Reduced login overhead
- Automatic session recovery
- Self-healing authentication
- Enterprise-grade session management

---

# Self-Healing Authentication Workflow

```text
Authenticated Test
        │
        ▼
Storage State exists?
        │
   ┌────┴────┐
   │         │
  No        Yes
   │         │
   ▼         ▼
Create   Validate Session
Storage        │
State          ▼
           Session valid?
             │
        ┌────┴────┐
        │         │
       Yes       No
        │         │
        ▼         ▼
 Run Test   Recreate Storage State
                  │
                  ▼
               Run Test
```

This mechanism makes the framework resilient against expired browser sessions and removes manual authentication steps from the test execution process.

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
    │   ├── auth
    │   ├── base
    │   ├── config
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
├── screenshots
├── traces
├── videos
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
- Self-Healing Authentication
- Automatic Storage State Validation
- Automatic Session Recovery
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
- BrowserStack Integration
- Allure Reporting
- HTML Reporting
- Docker Execution

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
- Self-healing authenticated sessions
- Automatic session validation and recovery
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
