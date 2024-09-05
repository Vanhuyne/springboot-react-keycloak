# SpringBoot-React-Keycloak Movie Recommendation System

## Overview

A microservices-based movie recommendation system showcasing the integration of Spring Boot, React, and Keycloak.

## Architecture

- **Frontend**: React with TypeScript
- **API Gateway**: Spring Boot
- **Movie Review Service**: Spring Boot with MongoDB
- **Authentication**: Keycloak

Key features:
- Microservices architecture
- Secure authentication
- Scalable and maintainable design

## Setup

1. Clone the repository
2. Configure MongoDB and Keycloak with docker compose file
3. Run services:
   - Frontend: `cd frontend && npm start`
   - API Gateway: `cd api-gateway && ./mvnw spring-boot:run`
   - Movie Review Service: `cd movie-review-service && ./mvnw spring-boot:run`

## Tech Stack

- Frontend: React 18, TypeScript, Tailwind CSS
- Backend: Spring Boot 3.3.3, Java 21
- Database: MongoDB
- Authentication: Keycloak

## Development

Ensure correct versions of Node.js, npm, and JDK 21 are installed.

For detailed component information, refer to respective directories and configuration files.