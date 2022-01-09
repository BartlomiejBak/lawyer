[![CircleCI](https://circleci.com/gh/BartlomiejBak/lawyer.svg?style=svg)](https://circleci.com/gh/BartlomiejBak/lawyer)

# Lawyer
Crm system for individual lawyer

## Table of Contents
1. [General info](#General-info)
2. [Technologies](#Technologies)
3. [Setup](#Setup)
4. [Status](#Status)


## General info
The purpose of this app is to simplify and improve the work of individual lawyers by organizing
all data related to their tasks. 

## Technologies
- Java 17 
####
- Spring Boot
- Maven
- PostgreSQL database
- Lombok
- Liquibase
#### Tests
- JUnit5
- Mockito
- Testcontainers
####
## Setup
The application is developed in IntelliJ IDEA Ultimate Edition.
Can be run via IDE. 

Database - app runs with official postgreSQL (version 14) docker image. To run application you need to pull docker image/install 
postgres locally with:

docker run --name postgres-latest -e POSTGRES_PASSWORD=foo -p 5432:5432 -d postgres:14


## Status
In progress...

To do/to be completed:
* Frontend (proper display of elements, graphics),
* Connection to external database (PostgreSQL),
* Logging in and access protection
* ...
