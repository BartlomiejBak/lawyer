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
- Java 11 
####
- Spring Boot 2.3.5 Release
- Maven 4.0.0
####
- H2 database
- Lombok
- JUnit5
- Mockito
####
## Setup
The application is developed in IntelliJ IDEA Ultimate Edition.
Can be run via IDE. 

Database - app runs with official postgreSQL docker image. To run application you need to pull docker image/install postgres locally
and change application.properties file:
- spring.datasource.url (49155 should be changed to chosen host port)
- spring.datasource.username
- spring.datasource.password

## Status
In progress...

To do/to be completed:
* Frontend (proper display of elements, graphics),
* Connection to external database (MySQL),
* Logging in and access protection
* ...
