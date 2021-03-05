# Technical_Assessment_EMBL_EBI
Technical Assessment for EBML-EBI

![image](https://user-images.githubusercontent.com/67907069/110125720-a55b2d00-7de9-11eb-85ae-ea73758acaab.png)


Person Utility operation using Java8, Spring Boot, Spring Security and H2 DB
RESTful API to simulate Person utility.

Requirements
CRUD operations for person entities.

Getting Started
Checkout the project from GitHub
git clone https://github.com/rvivek93/Technical_Assessment_EMBL_EBI.git

There will be two projects.
1. Assessment Project - having Person utility operations implemented.
2. Eureka Server - Used to register person service against eureka.

How to Run the Application:
1. First run the eureka server
2. Once Eureka server is up check using "http://localhost:8082/
3. Then, start the Assessment project by doing a maven build 
4. Then, Run as Spring boot app.
5. Make sure that the Person service is registered against Eureka.
6. Can use Postman Client to test the API's
7. Need to first generate the token by passing user name and password using POST API : http://localhost:8989/user?user="shinu"&password="test"
8. Once the token is received use that token to perform CRUD operation in Person utilities using below API's
   POST API : http://localhost:8989/persons/add 
   Header   : Key -> Authorization value : token generated
   
   GET API : http://localhost:8989/persons/all
   Header   : Key -> Authorization value : token generated
   
   PUT API : http://localhost:8989/persons/update
   Header   : Key -> Authorization value : token generated
   
   DELETE API : http://localhost:8989/persons/delete
   Header   : Key -> Authorization value : token generated
    

Enable Lombok support on your IDE
Refer to the following link for instructions:
https://projectlombok.org/setup/eclipse

Open IDE of your choice and Import as existing maven project in your workspace
- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App

Default port for the api is 8989
Prerequisites
Java 8
Spring Tool Suite 4 or similar IDE
Maven - Dependency Management
Maven Dependencies
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools
h2 - Inmemory database
lombok - to reduce boilerplate code
springfox-swagger2
springfox-swagger-ui
spring-boot-starter-test
spring-security-test

Swagger
Please find the Rest API documentation in the below url

http://localhost:8989/swagger-ui.html

H2 In-Memory Database
Make sure to use jdbc:h2:mem:testdb as your jdbc url. If you intend to you use custom database name, please define datasource properties in application.yml

http://localhost:8989/h2-console/

Testing the Person Utility API
Please use the Swagger url to see the API Documentation.

Author
Vivek Rajendran
