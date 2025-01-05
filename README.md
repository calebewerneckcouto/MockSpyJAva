ProductService Unit Tests README
This repository contains unit tests for the ProductService class of an e-commerce application, focusing on CRUD operations for products. The tests verify the behavior of various service methods, including insert and update, handling both valid and invalid inputs using Mockito to mock dependencies.

Table of Contents
Overview
Setup
Test Methods
Insert Method Tests
Update Method Tests
Running Tests
Technologies
License
Overview
The ProductServiceTests class is designed to test the core functionality of the ProductService in the e-commerce system. It includes tests for inserting and updating products, ensuring that the service behaves correctly when handling valid and invalid inputs such as null or blank names, invalid prices, and non-existing product IDs.

The test cases ensure that the service behaves as expected, throwing appropriate exceptions when invalid data is provided or when trying to update a non-existing product.

Setup
Before running the tests, ensure the following dependencies are included in your project:

JUnit 5
Mockito
Spring Test
For Maven, include the following dependencies:

xml
Copy code
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.9.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
Test Methods
Insert Method Tests
insertShouldReturnProductDTOWhenValidDate:

Tests that the insert method returns a valid ProductDTO when valid data is passed.
It checks that the name of the inserted product matches the expected value.
insertShouldReturnInvalidDataExceptionWhenProductNameIsBlank:

Verifies that an exception is thrown when the product name is blank.
insertShouldReturnInvalidDataExceptionWhenProductPriceIsNegativeOrZero:

Verifies that an exception is thrown when the product price is negative or zero.
Update Method Tests
updateShouldReturnProductDTOWhenIdExistsAndValidData:

Tests that the update method returns a valid ProductDTO when the product exists and valid data is provided.
updateShouldReturnInvalidDataExceptionWhenIdExistsAndProductNameIsBlank:

Verifies that an exception is thrown when the product name is blank for an existing product.
updateShouldReturnInvalidDataExceptionWhenIdExistsAndProductPriceIsNegativeOrZero:

Verifies that an exception is thrown when the product price is negative or zero for an existing product.
updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExistAndValidData:

Tests that a ResourceNotFoundException is thrown when attempting to update a non-existing product with valid data.
updateShouldReturnInvalidDataExceptionWhenIdDoesNotExistAndProductNameIsBlank:

Verifies that an exception is thrown when the product name is blank for a non-existing product.
updateShouldReturnInvalidDataExceptionWhenIdDoesNotExistAndProductPriceIsNegativeOrZero:

Verifies that an exception is thrown when the product price is negative or zero for a non-existing product.
Running Tests
To run the tests, you can use the following command with Maven:

bash
Copy code
mvn test
Alternatively, you can run the tests directly from your IDE (e.g., IntelliJ IDEA, Eclipse) by running the ProductServiceTests class.

Technologies
JUnit 5: Testing framework for unit tests.
Mockito: Mocking framework to create mock objects for testing.
Spring Boot: Framework for creating the e-commerce application.
Maven: Dependency management and build automation tool.
