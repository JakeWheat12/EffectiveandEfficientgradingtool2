# EffectiveandEfficientGradingTool Documentation 
Capstone Project 2021 Suny Albany

--------

# Objective
- Allowing users (graders) to generate existing comments from a local database
- Users are able to choose comments from the database, create their own comments, and export the selected comments as a PDF file

-----------------

# Requirement TOOLS
- MySQL database
- JVM(including JDK)

--------------------------

# SQL Script to generate tables for Users' Accounts 

- Assuming "Test" is our database name we created. 
- The password is the local password of the user's computer.
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

	USE Test;

      CREATE TABLE Comment(

       Comment_ID int NOT NULL PRIMARY KEY,

       Comment_text nvarchar(200) NOT NULL

    	);

----------------
