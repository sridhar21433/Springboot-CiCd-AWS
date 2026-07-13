# 🚀 End-to-End CI/CD Pipeline for Spring Boot Application

## 📌 Project Overview

This project demonstrates a complete end-to-end CI/CD pipeline for deploying a Spring Boot application using GitHub, Jenkins, Docker, Docker Hub, and AWS EC2.

Whenever code is pushed to GitHub, Jenkins automatically triggers the pipeline, builds the application, runs unit tests, creates a Docker image, pushes it to Docker Hub, and deploys the latest version to an AWS EC2 instance.

---

## 🏗️ Architecture

GitHub
↓
GitHub Webhook
↓
Jenkins
↓
Maven Build & Unit Tests
↓
Docker Image Build
↓
Docker Hub
↓
AWS EC2 (Docker Container)
↓
Live Spring Boot Application

---

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Git
- GitHub
- GitHub Webhooks
- Jenkins
- Docker
- Docker Hub
- AWS EC2 (Ubuntu)
- Linux
- SSH

---

## ✨ Features

- Automated CI/CD pipeline
- GitHub Webhook integration
- Automatic Jenkins build trigger
- Maven project build
- Unit test execution
- Docker image creation
- Docker Hub integration
- Automatic deployment to AWS EC2
- Live application deployment
- Pipeline failure on failed unit tests
- Automatic deployment verification

---

## 📂 Project Structure

```
Springboot-CiCd-AWS/
│
├── src/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── README.md
└── target/
```

---

## ⚙️ CI/CD Workflow

1. Developer pushes code to GitHub.
2. GitHub Webhook triggers Jenkins automatically.
3. Jenkins clones the latest source code.
4. Maven compiles the application.
5. JUnit executes unit tests.
6. If tests pass:
   - Docker image is built.
   - Image is pushed to Docker Hub.
   - Latest image is deployed to AWS EC2.
7. Application becomes available through the EC2 Public IP.

---

## 📦 Pipeline Stages

- Checkout Source Code
- Verify Tools
- Run Unit Tests
- Build Application
- Build Docker Image
- Push Image to Docker Hub
- Deploy to AWS EC2
- Verify Deployment

---

## 🔍 Tools Verification

The Jenkins pipeline verifies:

- Java
- Maven
- Git
- Docker

before starting the build process.

---

## 🧪 Unit Testing

The pipeline executes JUnit tests before building the application.

If any test fails:

- Build stops immediately.
- Docker image is not created.
- Deployment is skipped.

This prevents faulty code from reaching production.

---

## 🐳 Docker

The application is containerized using Docker.

The Docker image is automatically pushed to Docker Hub after every successful build.

---

## ☁️ AWS Deployment

Deployment is fully automated.

Jenkins connects to the AWS EC2 instance using SSH and deploys the latest Docker container.

---

## 🌐 Live Application

Example:

http://<EC2-Public-IP>:8081

---

## 📸 Screenshots

Add screenshots of:

- Jenkins Pipeline Success
- GitHub Webhook
- Docker Hub Repository
- AWS EC2 Instances
- Running Application

---

## 🎯 Learning Outcomes

Through this project I learned:

- CI/CD concepts
- Jenkins Pipelines
- GitHub Webhooks
- Docker containerization
- Docker Hub integration
- AWS EC2 deployment
- Linux command-line operations
- SSH-based remote deployment
- Build automation
- Continuous Integration
- Continuous Deployment

---

## 👨‍💻 Author

Sridhar R

AWS Cloud & DevOps Engineer