**Project**
-------------

This repository contains the Spring Boot based project for calculating the discounts on a retail website based on a set of given conditions.

**Project Description**
-----------------------

On a retail website, the following discounts apply:
1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.

Write a program in a Java – Spring boot with test cases such that given a bill, it finds the net payable amount.

**Getting Started**
--------------------

**Prerequisites**
-------------------

1) Spring Tools Suite or eclipse
2) Maven
3) Open JDK 8 or later
4) Knowledge of Core Java and Spring Boot

**Technologies Used**
---------------------

1) Spring Boot
2) Core Java
3) Java 8
4) JUnit

**How to clone the project**
---------------------------

1) Go to Code section of the Github project and click on the Code drop down button.
2) Copy the https url from the HTTPS section of the code drop down button.
3) In eclipse or STS IDE, Goto GIT perspective and click on clone a GIT repository option.
4) In the next pop-up window enter the URL copied from GIT. Enter the username and password and click on Next button.
5) In the next window, select the branch and again click on Next option. Next select the directory where you want to clone the project and click on Finish.

**How to run the project**
---------------------------

1) To run the project as a Spring Boot app or a Java application from eclipse or STS, right click on the test class and run as Java or Spring Boot Application.
2) To run as a Java application from command line, use any of the options - mvn package or mvn install.

**How to generate the test report**
------------------------------------

1) To run JUnit tests from eclipse or STS, right click on the test class and run as JUnit test.
2) To run JUnit tests from command line run the command mvn test.
3) The JUnit view will show the results (passed/failed tests).

**UML diagram**
----------------

![image](https://github.com/user-attachments/assets/23721f1a-0df6-4242-9934-8bfcd1a4b58b)

![image](https://github.com/user-attachments/assets/4f2e70ce-d4eb-446b-a0c9-bd9310c3c8ba)


**Solution Approach**
---------------------

1) Obtain the problem statement and requirements. Details given above in the project description section.
2) Setup the environment using the tools and technologies mentioned abobe in the Prerequisites and Technologies Used section.
3) Create a new project in Spring Tools Suite using Spring Starter Project option. Define the dependencies - web, JUnit etc.
4) Create a RestController that will consist of the API method fetchPayableAmount which returns the final payable amount after calculating discounts.
5) Define a DTO class for receiving the payment request with parameters such as bill and user type.
6) Create a service class. The controller calls the service method getPayableAmount in the service class to fetch the final payable amount after calculating discounts. Design the service and implementing classes keeping in mind the strategy design pattern for future purposes.
7) For example - Considering a list of items, here is how it will be calculated.
8) Get the product categories for each item from the utility class ProductCategoryWise. For this use the updatedProductsList method in the utility class. Most of the complex calculation logic is hidden from the service class. This is attributable to the use of Facade design pattern.
9) After that the getPayableAmount method internally calls the utility methods defined in the utility class DiscountCalculatorUtility to carry out operations such as calculating the total amount, grocery based amount, non grocery based amount, user based discounts, bill based discounts etc.
10) Use Spring bean annotations such as @RestController, @Service etc for automatic maintenance of beans by Spring IOC container.
11) For production, implement cross cutting concerns like Logging, exception handling etc.

**Sample scenario**
----------------------

1)  Total items - 5 || List of items - Biscuits (GROCERY), Toothpaste (GROCERY), Jeans (APPAREL), Mobile (ELECTRONICS), Pen (STATIONARY) with each costing 100$ || Total cost - 500$ || User Name - Supriyo Sarma || User type - CUSTOMER || Customer registration date - 19-SEP-2024.
2) An API request consisting of the list of products and their initial billable amount will be sent to the /discountedPayment API in CalculatePaymentController to generate the final payable amount. The product category won't be sent by the client.
3) The API method will call the getPayableAmount method in the service class ProductWiseDiscountService. This method will initially fetch the item wise product category from an utility class ProductCategoryUtility. After that the calculation will proceed as given below.
4) Calculate overall total amount using calculateTotal method in utility class which will come out to be 500$.
5) Calculate grocery total amount using calculateTotalByType method in utility class which will come out to be 200$.
6) Calculate non grocery total by subtracting grocery amount from total amount which will come out to be 300$.
7) Fetch the user based discount percentage from getUserDiscount method in utility class which will get the value 0 as customer is not more than 2 years old.
8) Calculate non grocery final amount from total amount using calculateDiscount method in utility class. Pass non grocery total and user based discount percentage as values. In this case, it will remain as 300.
9) Calculate bill based discount using the method calculateBillDiscount in utility class which will come out to be 25$ considering total 500$ bill.
10) Final amount is arrived at by subtracting the bill based discount from the sum of grocery total (step 5) and non grocery final amount (step 8). This will come out to be ((200+300) - 25) = 475$.
 

**Project Structure**
----------------------

![image](https://github.com/user-attachments/assets/ab26ff28-2a36-490b-95e4-f9d72b6eb0ed)
                
**Source Java Package Structure**
---------------------------------

![image](https://github.com/user-attachments/assets/bcfd61c6-4b98-4e73-846f-5beea3c07253)

**Source Java Test Package Structure**
--------------------------------------

![image](https://github.com/user-attachments/assets/fb5efa84-7870-4b09-99d7-89c8fcadefc6)
