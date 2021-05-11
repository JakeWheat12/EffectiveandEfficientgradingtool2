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
- This assumes you sign up with a username of tdawg123. Use find and replace to replace tdawg123 with your username if needed
--------------------------

# SQL Script to generate tables for Users' Accounts and Comments + Script to populate Comment Table

- The password is the local password of the user's computer.
- Remember, selecting the Charset as "utf8mb4" and Collation as "utf8mb4_unicode_520_ci"
- (normal utf8 or default is fine but will not allow you to use the emoji)

		CREATE TABLE user (
		UserName nvarchar(100) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		UserPassword nvarchar(100) NOT NULL,
		FirstName nvarchar(50) NOT NULL,
		LastName nvarchar(50) NOT NULL,
		Email nvarchar(100) NOT NULL
		);


      		CREATE TABLE comment (
    		Comment_ID int NOT NULL PRIMARY KEY,
    		User_FK nvarchar(100) NOT NULL,
    		Comment_text nvarchar(200) NOT NULL,
    		popularity int DEFAULT 0,
    		category VARCHAR(50),
   		positivity int not null,
    		FOREIGN KEY (User_FK) REFERENCES user(UserName)
		);
		
		INSERT INTO comment (User_FK, Comment_text, popularity, category, positivity) VALUES 
		("tdawg123", "Missing Comments", 0, "Computer Science", 1 ),
		("tdawg123", "Unused Imports", 0, "Computer Science", 1 ),
    		("tdawg123", "TODO statments not removed", 0, "Computer Science", 1 ),
    		("tdawg123", "Use of global varriables is not allowed", 0, "Computer Science", 1 ),
    		("tdawg123", "Solution is way off", 0, "Math", 1 ),
    		("tdawg123", "Order of Operations error", 0, "Math", 1 ),
    		("tdawg123", "Insufficient reasoning", 0, "Math", 1 ),
		("tdawg123", "Incorrect conclusion", 0, "Math", 1 ),
    		("tdawg123", "Frequent spelling errors", 0, "English", 1 ),
    		("tdawg123", "Frequent grammar errors", 0, "English", 1 ),
    		("tdawg123", "Wording is confusing", 0, "English", 1 ),
    		("tdawg123", "Multiple run on sentences", 0, "English", 1 ),
    		("tdawg123", "No heading, should include author, date, and a description", 0, "Computer Science", 2 ),
    		("tdawg123", "Alorithm is overly complex", 0, "Computer Science", 2 ),
    		("tdawg123", "Methods created with no purpose", 0, "Computer Science", 2 ),
    		("tdawg123", "Confusing variable labels", 0, "Computer Science", 2 ),
    		("tdawg123", "Correct method, incorrect result", 0, "Math", 2 ),
    		("tdawg123", "Minor computation error", 0, "Math", 2 ),
    		("tdawg123", "Rounding error", 0, "Math", 2 ),
    		("tdawg123", "Answer lacks the necessary units, or forgot the units altogether", 0, "Math", 2 ),
    		("tdawg123", "Writting is unessecarily wordy", 0, "English", 2 ),
    		("tdawg123", "Incorrect heading", 0, "English", 2 ),
    		("tdawg123", "Few spelling errors", 0, "English", 2 ),
    		("tdawg123", "Few grammar errors", 0, "English", 2 ),
    		("tdawg123", "Solution is adequate", 0, "Computer Science", 3 ),
    		("tdawg123", "Comments are adequate", 0, "Computer Science", 3 ),
    		("tdawg123", "Methods all have purpose and are well organized", 0, "Computer Science", 3 ),
    		("tdawg123", "Solution is adequately efficient", 0, "Computer Science", 3 ),
    		("tdawg123", "Mostly correct, but few trivial errors", 0, "Math", 3 ),
    		("tdawg123", "Almost no spelling errors", 0, "English", 3 ),
    		("tdawg123", "Almost no grammar errors", 0, "English", 3 ),
    		("tdawg123", "Appears to be well organized", 0, "English", 3 ),
    		("tdawg123", "Writing flows naturally", 0, "English", 3 ),
    		("tdawg123", "Good introduction", 0, "English", 3 ),
    		("tdawg123", "Good anecdote", 0, "English", 3 ),
    		("tdawg123", "Good conclusion paragraph", 0, "English", 3 ),
    		("tdawg123", "Most efficient solution possible", 0, "Computer Science", 4 ),
	    	("tdawg123", "Great comments", 0, "Computer Science", 4 ),
    		("tdawg123", "Mehtods well explained", 0, "Computer Science", 4 ),
    		("tdawg123", "Classes and methods are well organized and named", 0, "Computer Science", 4 ),
    		("tdawg123", "Very efficient solution", 0, "Computer Science", 4 ),
    		("tdawg123", "Elegant design", 0, "Computer Science", 4 ),
    		("tdawg123", "Perfect solution", 0, "Math", 4 ),
    		("tdawg123", "Well done", 0, "Math", 4 ),
    		("tdawg123", "Bonus points", 0, "Math", 4 ),
    		("tdawg123", "No spelling errors", 0, "English", 4 ),
    		("tdawg123", "No grammar mistakes", 0, "English", 4 ),
    		("tdawg123", "Great hook/opening paragraph", 0, "English", 4 ),
    		("tdawg123", "No mistakes, perfect", 0, "English", 4 ),
    		("tdawg123", "Conclusion wraps it up nicely, good job", 0, "English", 4 );
	



----------------
