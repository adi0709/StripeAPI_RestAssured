# **Testing StripeAPI using RestAssured**

This repo contains automation tests for [Stripe API](https://docs.stripe.com/api) using RestAssured.

## **#Requirments**
- JAVA
- MAVEN 
- IDE of your choice

## **#Setting Up**

- Download the repo to your local system.
- Make sure you have all the dependencies required to execute the tests ready like Maven, JDK and JAVA installed.
- Open the project in IDE of your choice.
- Install TestNg plugin to your IDE in case you are using Eclipse, In case of IntelliJ TestNg is available by default.
- Download the JDK if your system is missing it.
- Execute the testng.xml by right-clicking it and selecting "Run".

## **#Pre conditions for executing tests**
- Create a stripe account to access the data.
- Get the secret key from the dev options from Stripe Dev portals.
- Place the secret key in `./src/test/resources.properties/config.properties` file.
- To create new user/users add the name, email and description in `./src/test/resources/Excel/testData.xlsx` file. 
- To delete a user/users add the id's of the users in `./src/test/resources/Excel/testData.xlsx` file.

## **#Executing tests and Viewing reports**
The tests can be executed by either of the following methods:
- Execute the testng.xml by right-clicking it and selecting "Run".
- Execute the command `mvn clean` to clean the target folder, followed by `mvn test` to execute the tests.
- The reports can be accessed in the Reports folder.
