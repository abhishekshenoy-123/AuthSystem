<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f0c29,50:302b63,100:24243e&height=200&section=header&text=🔐%20AuthSystem&fontSize=52&fontColor=ffffff&fontAlignY=38&desc=Spring%20Boot%20%7C%20Token%20Auth%20%7C%20GitHub%20OAuth2&descAlignY=58&descSize=18&descColor=a0aec0" width="100%"/>

<br/>

[![Java](https://img.shields.io/badge/Java_17+-f89820?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-005F0F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![H2](https://img.shields.io/badge/H2_Database-1a1aff?style=for-the-badge&logoColor=white)](https://www.h2database.com/)
[![GitHub OAuth](https://img.shields.io/badge/GitHub_OAuth2-181717?style=for-the-badge&logo=github&logoColor=white)](https://docs.github.com/en/apps/oauth-apps)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

<br/>

> **A full-stack authentication system** built with Spring Boot, featuring UUID token sessions,  
> GitHub OAuth2 social login, user registration, and a secured REST API — all backed by an H2 in-memory database.

<br/>

[![Features](https://img.shields.io/badge/─────────────────────────────────────────────────────────-0f0c29?style=for-the-badge)](.)

</div>

<br/>

## 📋 Table of Contents

<div align="center">

| | Section | | Section |
|:---:|:---|:---:|:---|
| 🌟 | [Features](#-features) | 🔄 | [Auth Flow](#-auth-flow) |
| 📁 | [Project Structure](#-project-structure) | 🛠️ | [Prerequisites](#️-prerequisites) |
| 🚀 | [Setup & Installation](#-setup--installation) | 📡 | [API Reference](#-api-reference) |
| 🌐 | [Web Routes](#-web-routes) | 🗄️ | [H2 Console](#️-h2-console) |
| 🧰 | [Tech Stack](#-tech-stack) | ⚠️ | [Security Notes](#️-security-notes) |

</div>

<br/>

---

<div align="center">

### 🌟 Features

</div>

<br/>

<div align="center">

| &nbsp;&nbsp;&nbsp;🪙&nbsp;&nbsp;&nbsp; | **Token Authentication** | UUID tokens with 2-hour expiry stored & validated in H2 |
|:---:|:---|:---|
| &nbsp;&nbsp;&nbsp;🐙&nbsp;&nbsp;&nbsp; | **GitHub OAuth2 Login** | Social login via GitHub with auto user provisioning |
| &nbsp;&nbsp;&nbsp;🛡️&nbsp;&nbsp;&nbsp; | **Spring Security** | Per-route filter chain with full access rule control |
| &nbsp;&nbsp;&nbsp;🗃️&nbsp;&nbsp;&nbsp; | **JPA + H2 Database** | In-memory persistence — zero-config dev & test setup |
| &nbsp;&nbsp;&nbsp;📝&nbsp;&nbsp;&nbsp; | **User Registration** | Username & password signup with duplicate detection |
| &nbsp;&nbsp;&nbsp;🔒&nbsp;&nbsp;&nbsp; | **Secured REST API** | Token-protected endpoints with Authorization header |

</div>

<br/>

---

## 🔄 Auth Flow

<br/>

### 🔑 &nbsp; Username / Password

```
┌─────────────────┐     ┌─────────────────┐     ┌──────────────────┐     ┌──────────────────┐
│  POST /login    │     │   AuthService   │     │   UUID Token     │     │ GET /secure-data │
│  credentials   │────▶│  validate user  │────▶│ saved + returned │────▶│ Authorization    │
│                 │     │                 │     │ (expires: 2h)    │     │ header required  │
└─────────────────┘     └─────────────────┘     └──────────────────┘     └──────────────────┘
```

<br/>

### 🐙 &nbsp; GitHub OAuth2

```
┌──────────────────┐     ┌──────────────────────┐     ┌─────────────────────────┐
│ Login w/ GitHub  │     │   OAuth2 Handshake   │     │       /success          │
│   (user click)  │────▶│   Spring Security    │────▶│  auto-provision user    │
│                  │     │   token exchange     │     │  save to DB if new      │
└──────────────────┘     └──────────────────────┘     └─────────────────────────┘
```

<br/>

---

## 📁 Project Structure

```
🗂  AuthSystem/
│
├── 🚀  AuthSystemApplication.java         ← Spring Boot entry point
│
├── 📂  SecurityConfig/
│   └── 🔒  AppSecurityConfig.java         ← Filter chain · OAuth2 · route access rules
│
├── 📂  controller/
│   ├── 📡  AuthController.java            ← REST  →  /api/auth/login  &  /secure-data
│   └── 🌐  WebController.java            ← Web   →  /  ·  /register  ·  /success
│
├── 📂  model/
│   ├── 👤  User.java                      ← User entity (id · username · password · role)
│   └── 🪙  AuthToken.java                 ← Token entity (UUID · username · expiryDate)
│
├── 📂  repository/
│   ├── 🗄️  UserRepository.java            ← JPA → users table
│   └── 🗄️  TokenRepository.java           ← JPA → auth_tokens table
│
└── 📂  service/
    └── ⚙️  AuthService.java               ← Login logic · token generation · validation
```

<br/>

---

## 🛠️ Prerequisites

<br/>

<div align="center">

| Requirement | Version | Notes |
|:---:|:---:|:---|
| ☕ **Java** | `17+` | JDK required |
| 📦 **Maven** | `3.8+` | Build & dependency management |
| 🐙 **GitHub OAuth App** | — | Needed for social login |

</div>

<br/>

---

## 🚀 Setup & Installation

<br/>

### `Step 1` &nbsp; — &nbsp; Clone the repository

```bash
git clone https://github.com/your-username/AuthSystem.git
cd AuthSystem
```

<br/>

### `Step 2` &nbsp; — &nbsp; Create a GitHub OAuth App

Go to **[github.com/settings/developers](https://github.com/settings/developers)** → New OAuth App and configure:

```
Homepage URL              →   http://localhost:8080
Authorization Callback    →   http://localhost:8080/login/oauth2/code/github
```

<br/>

### `Step 3` &nbsp; — &nbsp; Add credentials to `application.properties`

```properties
# ── GitHub OAuth2 ───────────────────────────────────────────────────────────
spring.security.oauth2.client.registration.github.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_CLIENT_SECRET

# ── H2 Console (dev only) ───────────────────────────────────────────────────
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```

<br/>

### `Step 4` &nbsp; — &nbsp; Run the application

```bash
mvn spring-boot:run
```

> 🟢 &nbsp; App running at &nbsp;**`http://localhost:8080`**

<br/>

---

## 📡 API Reference

<br/>

### &nbsp; `POST` &nbsp; `/api/auth/login`

> Authenticate with username and password to receive a **UUID session token**.

<br/>

**📥 &nbsp; Request Parameters** &nbsp; *(form-data)*

| Parameter | Type | Required | Description |
|:---|:---:|:---:|:---|
| `username` | `String` | ✅ | Registered username |
| `password` | `String` | ✅ | Account password |

**📤 &nbsp; Responses**

| Status | Meaning | Body |
|:---:|:---|:---|
| `200 OK` | ✅ Success | UUID token string |
| `401 Unauthorized` | ❌ Bad credentials | `Invalid Credentials` |

**💻 &nbsp; Example**

```bash
curl -X POST http://localhost:8080/api/auth/login \
     -d "username=john&password=secret"

# Response:
# 550e8400-e29b-41d4-a716-446655440000
```

<br/>

---

### &nbsp; `GET` &nbsp; `/api/auth/secure-data`

> Access a protected resource using a **valid session token**.

<br/>

**📥 &nbsp; Request Header**

| Header | Value |
|:---|:---|
| `Authorization` | UUID token from `/api/auth/login` |

**📤 &nbsp; Responses**

| Status | Meaning | Body |
|:---:|:---|:---|
| `200 OK` | ✅ Valid token | `Welcome! You have accessed secure data...` |
| `403 Forbidden` | ❌ Invalid / expired | `Access Denied: Invalid Token` |

**💻 &nbsp; Example**

```bash
curl http://localhost:8080/api/auth/secure-data \
     -H "Authorization: 550e8400-e29b-41d4-a716-446655440000"

# Response:
# Welcome! You have accessed secure data using your token.
```

<br/>

---

## 🌐 Web Routes

<br/>

<div align="center">

| Method | Route | View | Description |
|:---:|:---|:---|:---|
| `GET` | `/` | `index.html` | 🏠 Home / login landing page |
| `GET` | `/register` | `register.html` | 📝 User registration form |
| `POST` | `/register` | — | ✅ Submit & save new user |
| `GET` | `/success` | `success.html` | 🎉 Post-login success (OAuth2 & regular) |

</div>

<br/>

---

## 🗄️ H2 Console

The embedded H2 database console is accessible during development:

```
🌐  URL        →   http://localhost:8080/h2-console
🔗  JDBC URL   →   jdbc:h2:mem:testdb
👤  Username   →   sa
🔑  Password   →   (leave blank)
```

> [!NOTE]
> The console is enabled via `AppSecurityConfig` which permits `/h2-console/**`.  
> Disable it before deploying to any non-local environment.

<br/>

---

## 🧰 Tech Stack

<br/>

<div align="center">

| Technology | Version | Purpose |
|:---:|:---:|:---|
| [![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot) | `3.x` | Core application framework |
| [![Spring Security](https://img.shields.io/badge/Spring_Security-005F0F?style=flat-square&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security) | `6.x` | Authentication & authorization |
| [![OAuth2](https://img.shields.io/badge/OAuth2_Client-181717?style=flat-square&logo=github&logoColor=white)](https://docs.github.com/en/apps/oauth-apps) | — | GitHub social login |
| [![JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa) | — | ORM & database access |
| [![H2](https://img.shields.io/badge/H2_Database-1a1aff?style=flat-square&logoColor=white)](https://www.h2database.com/) | — | In-memory dev database |
| [![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/) | — | Server-side HTML templates |

</div>

<br/>

---

## ⚠️ Security Notes

<br/>

> [!WARNING]
> This project is built for **learning and development only**.  
> Do **not** deploy to production without addressing the points below.

<br/>

| Severity | Issue | Recommended Fix |
|:---:|:---|:---|
| 🔴 **Critical** | Passwords stored in plain text | Use `BCryptPasswordEncoder` |
| 🔴 **Critical** | CSRF protection is disabled | Re-enable for form-based apps |
| 🟡 **Medium** | H2 is in-memory only | Use PostgreSQL / MySQL for production |
| 🟡 **Medium** | UUID tokens are unsigned | Replace with signed JWT tokens |
| 🟢 **Low** | H2 console is publicly accessible | Disable in `application.properties` |

<br/>

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:24243e,50:302b63,100:0f0c29&height=120&section=footer" width="100%"/>

**Built with** ☕ **Java &** 🍃 **Spring Boot**

[![GitHub](https://img.shields.io/badge/⭐_Star_this_repo-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/your-username/AuthSystem)

</div>
