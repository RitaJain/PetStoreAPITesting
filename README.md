# PetStore API Testing

## Table of contents
* PetStore API Testcases
* [Technologies](#technologies)
* [Setup](#setup)
* [Folder structure conventions](#folder-structure-conventions)
* [Test execution](#test-execution)


General info
============
This automation project aims to help developers to detect and prevent any issues releated to PetStore API available
at url https://petstore.swagger.io/

Technologies
============
- Java Compiler 1.8 : used to compile the project
- Testng 7.1.0 - Support for data-driven testing (with @DataProvider)and other Annotations (@Test , @BeforeTest, @AfterTest)
- MVN  3.6.3 - build automation tool

Setup
=====
Ubuntu OS installation

* Install Java JDK 8 on your local machine
* Install Maven
$ mvn -v
* Install IntelliJ IDEA please follow the steps [here](https://www.jetbrains.com/idea/)
* Launch IntelliJ IDEA and click on `Import project

Folder structure conventions
============================

> Folder structure for an automation software project

### A top-level directory layout
    .
    ├── src                     # Source files
    ├── test-output             # Test report generated files by Testng
    ├── README.md               # Project Details
    ├── Testng.xml              # Testsuite
    ├── target                  # Internal test report generated files

### Source files
    .
    ├── ...
    ├── src
    │   │
    │   └── main
    │       ├── java
    │       │    ├── files
    │       │    │   ├── Payloads   # contains payloads for Create user and adding of new pet
    │       │    │   ├── PetStoreApiTest   # contains testcases for API testing
    │       │    │   ├── ResusableMethods   # contains Resuable Java Methods which are used in PetStoreApiTest
    │       │
    │



    #Test Execution
    Maven  tool is used for the project build and dependencies configuration.
    Execution from a terminal window example:
    $ mvn clean test

   Reporting
   =========
   Testng Default reporting listner is used for generation of reports can be found  in test-output folder  named with below names
   
    - Index.html
    - emailable-report.html
