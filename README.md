**Project**

This repository contains the Spring Boot based project for calculating the discounts on a retail website based on a set of given conditions.

**Project Description**

On a retail website, the following discounts apply:
1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.

Write a program in a Java – Spring boot with test cases such that given a bill, it finds the net payable amount.

**Features**

**Getting Started**
**Prerequisites**

1) Spring Tools Suite or eclipse
2) Maven
3) Open JDK 8 or later
4) Knowledge of Core Java and Spring Boot

**Technologies Used**

1) Spring Boot
2) Core Java
3) Java 8
4) JUnit

**How to run the project**
1) To run JUnit tests from eclipse or STS, right click on the test class and run as JUnit test.
2) To run JUnit tests from command line run the command mvn test.
3) To run as a Java application from eclipse or STS, right click on the test class and run as Java or Spring Boot Application.
4) To run as a Java application from command line, use any option mvn package or mvn install.

**Solution Approach**
1) Obtain the problem statement and requirements. Details given above in the project description section.
2) Setup the environment using the tools and technologies mentioned abobe in the Prerequisites and Technologies Used section.
3) Create a new project in Spring Tools Suite using Spring Starter Project option. Define the dependencies - web, JUnit etc.
4) Create a RestController that will consist of the API method fetchPayableAmount which returns the final payable amount after calculating discounts.
5) Define a DTO class for receiving the payment request with parameters such as bill and user type.
6) Create a service class. The controller calls the service method getPayableAmount in the service class to fetch the final payable amount after calculating discounts.
7) For example - If there are 5 items - Biscuits, Toothpaste, Jeans, Mobile, Pen. Here is how it will be calculated.
8) Get the product categories for each item from the utility class ProductCategoryWise.
9) After thar the getPayableAmount method internally calls the utility methods defined in the utility class DiscountCalculatorUtility to carry out operations such as calculating the total amount, grocery based amount, non grocery based amount, user based discounts, bill based discounts etc.

**Project Structure**

├───.mvn
│   └───wrapper
├───.settings
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───spring
│   │   │           └───retail
│   │   │               ├───controller
    │       └───maven
    │           └───com.spring.retail
    │               └───spring-retail-project
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-status
    │   └───maven-compiler-plugin
    │       ├───compile
    │       │   └───default-compile
    │       └───testCompile
    │           └───default-testCompile
    └───test-classes
        └───com
            └───spring
                └───retail
