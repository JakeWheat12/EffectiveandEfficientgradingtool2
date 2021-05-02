# EffectiveandEfficientGradingTool Documentation 
Capstone Project 2021 Suny Albany

--------

# Objective
- Allowing users (graders) to generate some existed comments from the local database
- Storing comments into the file and being able to exported as the PDF(or WORD.doc) format locally

-----------------

# Requirement TOOLS
- MySQL database
- JVM(including JDK)

--------------------------

# SQL Script to generate tables for Users' Accounts 

- Assuming "Test" is our database name we created
- Remember, selecting the Charset as "utf8mb4" and Collation as "utf8mb4_unicode_520_ci"
- (normal utf8 or default is fine but will not allow you to use the emoji)

      USE Test;

      CREATE TABLE User(

       UserName nvarchar(100) NOT NULL PRIMARY KEY,

       UserPassword nvarchar(100) NOT NULL,

       FirstName nvarchar(50) NOT NULL,

       LastName nvarchar(50) NOT NULL,

       Email nvarchar(100) NOT NULL

    );  

----------------
