# Production Deployment of a Containerized Three-Tier Java Application using Docker Swarm and DevSecOps Practices

## Project Overview

This project demonstrates the end-to-end deployment of a containerized three-tier Java Quiz Application using DevOps and DevSecOps practices.

The solution integrates:

* GitHub for source control
* Jenkins for CI/CD
* SonarQube for code quality analysis
* Nexus Repository for artifact management
* Trivy for image security scanning
* Docker for containerization
* Docker Hub as image registry
* Docker Swarm for orchestration
* Docker Secrets for credential management
* MySQL as backend database

---

## Architecture

### Presentation Tier

* Browser-based Quiz Application

### Application Tier

* Java (JSP, Servlets, JDBC)
* Apache Tomcat

### Data Tier

* MySQL Database

---

## CI/CD Workflow

1. Code pushed to GitHub
2. Jenkins pipeline triggered
3. SonarQube code quality analysis
4. Maven build and package
5. WAR artifact uploaded to Nexus
6. Docker images built
7. Trivy vulnerability scan executed
8. Images pushed to Docker Hub
9. Docker Swarm deployment performed

---

## Docker Swarm Setup

* 1 Manager Node
* 2 Worker Nodes
* Overlay Networks
* Docker Secrets
* Multi-replica application deployment

---

## Nexus Repository Usage

### Snapshot Repository

Used during development.

Example:

```text
1.0-SNAPSHOT
```

Allows continuous uploads and generates timestamp-based versions automatically.

### Release Repository

Used for stable production releases.

Example:

```text
1.0
1.1
2.0
```

Release artifacts cannot be overwritten.

---

## Docker Secrets

Implemented:

* MYSQL_ROOT_PASSWORD
* MYSQL_PASSWORD

Secrets are managed within Docker Swarm instead of exposing credentials directly in deployment files.

---

## Challenges Faced & Resolutions

### SonarQube Build Failure

**Issue:**
SonarQube scan failed because Maven was executed outside the project directory.

**Resolution:**
Used Jenkins `dir()` step to execute Maven from the folder containing `pom.xml`.

---

### Nexus Artifact Upload Failure

**Issue:**
Artifact upload failed due to incorrect repository type and version mismatch.

**Resolution:**
Configured Snapshot repository correctly and aligned Maven version with Nexus repository policy.

---

### Docker Build Failure

**Issue:**
Jenkins could not find Docker even though Docker was installed on the Swarm Manager.

**Resolution:**
Discovered Jenkins agent was connected to the wrong machine. Updated node configuration to point to the actual Swarm Manager.

---

### Nexus Service Startup Failure

**Issue:**
Nexus was inaccessible after restart.

**Resolution:**
Identified permission issues in the `sonatype-work` directory and corrected ownership.

---

### Docker Swarm Image Deployment Failure

**Issue:**
Services failed with "No such image".

**Resolution:**
Images were built locally but not available on worker nodes. Pushed images to Docker Hub and deployed using registry-hosted images.

---

### Application Login Session Issue

**Issue:**
Users were occasionally redirected back to the login page when multiple replicas were running.

**Resolution:**
Identified that HTTP sessions were stored locally inside Tomcat containers while requests were being load-balanced across replicas.

---

## Current Limitation

The application supports multiple replicas in Docker Swarm, but user sessions are stored locally within each application container.

Because of this, a user may be redirected to the login page if requests are routed to a different replica.

---

## Future Enhancements

* Redis-backed session management
* Sticky session configuration
* MySQL replication
* Kubernetes deployment

---

## Screenshots

All screenshots are available in:

```text
snapshots.md
```

Including:

* Jenkins Pipeline
* SonarQube Analysis
* Nexus Repository
* Docker Hub Images
* Trivy Scan Results
* Docker Secrets
* Docker Swarm Cluster
* Application Screenshots

---

## Key Learning Outcomes

* CI/CD Pipeline Automation
* DevSecOps Integration
* Artifact Lifecycle Management
* Docker Swarm Orchestration
* Secret Management
* Container Security Scanning
* High Availability Concepts
* Troubleshooting Production Deployment Issues

---

## Author

**Navya Sri** - 9th June 2026
