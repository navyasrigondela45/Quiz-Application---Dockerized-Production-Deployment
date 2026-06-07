# Quiz Application - Dockerized Pre-Production Deployment

## Overview

This project is a Java-based Quiz Application deployed in a Dockerized Pre-Production Environment.

The application allows users to register, log in, take quizzes, review results, and track previous attempts. The entire solution is containerized using Docker and orchestrated using Docker Compose.

This project was built to gain hands-on experience in application containerization, multi-container deployments, database integration, and real-world troubleshooting of containerized applications.

---

## Features

### User Management
- User Registration
- User Login Authentication
- Secure User Data Storage

### Quiz Engine
- Question Bank containing 50 Questions
- Random Selection of 10 Questions per Attempt
- Different Question Sets on Every Retake

### Results Processing
- Instant Score Calculation
- Correct Answer Review
- Incorrect Answer Identification

### Attempt History
- View Previous Quiz Attempts
- Track Historical Scores
- Review Performance Progress

---

## Technology Stack

| Layer | Technology |
|---------|------------|
| Frontend | JSP, HTML, CSS |
| Backend | Java Servlets |
| Build Tool | Maven |
| Database | MySQL 8 |
| Web Server | Apache Tomcat 9 |
| Containerization | Docker |
| Orchestration | Docker Compose |
| Version Control | Git & GitHub |

---

## Architecture

```text
+-------------+
|   Browser   |
+------+------+
       |
       v
+--------------------+
| Quiz App Container |
|     Tomcat 9       |
+---------+----------+
          |
          |
          v
+--------------------+
|   MySQL Container  |
|      quiz_db       |
+--------------------+
```

---

## Docker Components

### Application Container
Responsible for:
- Hosting the Java Quiz Application
- Serving Quiz Pages
- Processing User Requests
- Managing Quiz Results

### Database Container
Responsible for:
- User Data Storage
- Quiz Questions Storage
- Attempt History Storage
- Results Management

### Docker Compose
Used for:
- Multi-Container Deployment
- Service Networking
- Environment Configuration
- Simplified Application Startup

---

## Challenges Faced During Development

This project involved extensive troubleshooting and practical learning while deploying a containerized Java application.

### Docker Networking Challenges
- Container-to-Container communication failures
- Service name resolution troubleshooting
- Application unable to connect to MySQL container

### JDBC Configuration Issues
- Incorrect database host references
- JDBC URL validation

### MySQL Connectivity Issues
- Communication Link Failure errors
- Connection timeout troubleshooting
- Database initialization validation

### Authentication Challenges
- MySQL 8 authentication plugin compatibility
- Public Key Retrieval errors
- User privilege configuration
- mysql_native_password migration troubleshooting

### Deployment Challenges
- WAR deployment validation
- Tomcat startup troubleshooting
- Container rebuild and redeployment verification
- Docker image version consistency checks

---

## Project Structure

<img width="332" height="461" alt="image" src="https://github.com/user-attachments/assets/fed0a425-d72e-4a69-90b1-771a80f62415" /> <img width="320" height="476" alt="image" src="https://github.com/user-attachments/assets/766ccad6-11c3-48b7-b8e4-32a987443f4c" /> <img width="370" height="463" alt="image" src="https://github.com/user-attachments/assets/b89f8be2-4dda-4d03-a93f-73ad96c68d37" />

---

## How to Run

### Clone Repository

```bash
git clone <repository-url>
cd quiz-app
```

### Build Application

```bash
mvn clean package
```

### Start Containers

```bash
docker-compose up -d
```

### Verify Running Containers

```bash
docker ps
```

### Access Application

```text
http://<server-ip>:<host-port>
```

---

## Screenshots

### Application
- Login Page
  <img width="959" height="458" alt="image" src="https://github.com/user-attachments/assets/a8c27f8b-64f0-4814-b471-bcc33148f42a" />

- Registration Page
  <img width="959" height="473" alt="image" src="https://github.com/user-attachments/assets/63a76a69-0cc2-4cea-b2d5-dc7ca8c48717" />

- Quiz Questions Page
  <img width="959" height="476" alt="image" src="https://github.com/user-attachments/assets/00661e22-cc20-4ae7-84e6-ade5e74b073f" />

- Quiz Result and Answer Review Page
  <img width="959" height="474" alt="image" src="https://github.com/user-attachments/assets/d4c26824-6b32-4875-8a04-5784f31ee293" />

- Attempt History Page
- <img width="959" height="470" alt="image" src="https://github.com/user-attachments/assets/48e5e971-f361-46db-91a0-3d04f3ad7b21" />

### Docker
- Docker Images
  <img width="519" height="66" alt="image" src="https://github.com/user-attachments/assets/a62fd36c-4808-47b3-9d3e-65d0be50b3b1" />

- Running Containers
  <img width="959" height="71" alt="image" src="https://github.com/user-attachments/assets/258c90f2-9dc2-415a-8b7a-be4eb86cbcf3" />

- Docker Compose Deployment
  <img width="737" height="88" alt="image" src="https://github.com/user-attachments/assets/e675a9da-ee7c-4ba2-8aec-4d832e4200b2" />

### Database
- Users Table
  <img width="874" height="168" alt="image" src="https://github.com/user-attachments/assets/fd7134e0-348d-4536-87eb-a29cdb73f22e" />

- Quiz Questions Table
  <img width="941" height="473" alt="image" src="https://github.com/user-attachments/assets/783a146a-fb62-40f7-aa2e-c87b2aeebd43" />

- Quiz Results Table
  <img width="439" height="163" alt="image" src="https://github.com/user-attachments/assets/8cc19a07-7c83-425b-9cb6-67dc34a6129d" />

---

## Current Deployment Status

### Pre-Production Environment

This project is currently deployed in a Docker Compose based Pre-Production Environment for learning, testing, and validation purposes.

### Current Limitations

- Single Host Deployment
- Docker Compose Based Deployment
- No High Availability
- No Service Replication
- No Automatic Failover
- Single Point of Failure
- Manual Recovery Process

These limitations are expected and will be addressed in future iterations as part of the production-readiness roadmap.

---

## Future Enhancements

### CI/CD
- Jenkins Pipeline Integration
- Automated Build Process
- Automated Docker Image Creation
- Automated Deployment Workflow

### Container Orchestration
- Docker Swarm
- Docker Stack Deployments
- Multi-Replica Services
- High Availability Architecture

### Security
- Docker Secrets
- Secure Credential Management
- Environment Hardening
- 
---

## Learning Outcomes

Through this project, I gained practical experience in:

- Docker Image Creation
- Multi-Container Deployments
- Docker Compose
- Container Networking
- Java Application Containerization
- MySQL Containerization
- Docker Troubleshooting
- Database Connectivity Debugging
- Pre-Production Deployment Practices
- Infrastructure Problem Solving

---

## Author

Navya Sri

If you would like to explore, test, or contribute to this project, feel free to check out the GitHub repository and share your feedback.
