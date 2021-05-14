# CommentGenerator Documentation 
Capstone Project 2021 Suny Albany

--------

# Objective
- Allowing users (graders) to generate existing comments from a local database
- Users are able to choose comments from the database, create their own comments, and export the selected comments as a PDF file

-----------------

# Requirement TOOLS
- MySQL database (mysql connector jar file is needed)
- JVM(including JDK)

--------------------------

# Things Users should update before using
- the database information (since they are all independent)
- update the header information with your own corresponding MySql database information in UserRegisterPage.class, UserLoginPage.class, Database.class 
	- EX:	 private static final String DB_URL = "jdbc:mysql://localhost:3306/Test";
		 private static final String USER = "root";
		 private static final String PASSWORD = "dlx990330";
		 
		3306 is usually the default port number when you create the local database. "Test" is the database name which you can create your own name as well.
		
		USER="root": "root" is the default name. 
		PASSWORD is usually your laptop's password or local computer's password.
-----------------

# SQL Script to generate tables for Users' Accounts 

- Assuming "Test" is our database name we created. 
- The password is the local password of the user's computer.
- Remember, selecting the Charset as "utf8mb4" and Collation as "utf8mb4_unicode_520_ci"
- (normal utf8 or default is fine but will not allow you to use the emoji)

------------------------
 User Table (UserName is unique)
-----------------------

      USE Test;

      CREATE TABLE User(

       UserName nvarchar(100) NOT NULL PRIMARY KEY,

       UserPassword nvarchar(100) NOT NULL,

       FirstName nvarchar(50) NOT NULL,

       LastName nvarchar(50) NOT NULL,

       Email nvarchar(100) NOT NULL

		);  
	
	
	
	

	
-------------------
 Comment Table (Comment's Category: 1 positive/ 0 negative)
------------------

   	USE Test;
   
    CREATE TABLE Text(
    
    Comment_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    
    Content nvarchar(250) NOT NULL,
    
    Category INT NOT NULL
    
   	);

----------------
Sample data for Comments
----------------

	 INSERT INTO Text(Content, Category)
	  VALUES
	  ("Correct method, incorrect result", 0),
	  ("Minor computation error",0),
	  ("Methods created with no purpose", 0),
	  ("Frequent grammar errors", 0),
	  ("Conclusion wraps it up nicely, good job", 1),
	  ("No grammar mistakes", 1),
	  ("Logic is clear, good job!", 1),
	  ("Very efficient solution", 1),
	  ("provide implementation details and demonstrate code", 0),
	  ("Anything not obvious has reasoning ",1),
	  ("Methods created with no purpose", 0),
	  ("Lots of abbreviations ", 0),
	  ("Missing major part of assignment", 0),
	  ("Missing basic requirements", 0),
	  ("Very good attention to detail", 1),
	  ("Good overall design", 1),
	  ("Poor organization of ideas", 0),
	  ("Poor name variable naming",0),
	  ("All functions implemented correctly", 1),
	  ("Could be more specific here", 0),
	  ("Excellent job conveying ideas", 1),
	  ("Incorrect use of function", 0),
	  ("Close but missing one key point", 0),
	  ("Missing major part of assignment", 0);
	  
---------
# Note
- The functionality of recoverying user's credential is by google email verificaion. However, currently the funtionality will make users's google accounts insecure. So we are still improving it. Appreciate for the understanding 🙏
--------
