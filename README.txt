The "Recipe Manager" was a group project that I worked on in Spring 2014 for my Software Development class. 

My group for this project consisted of two students. I was the backend designer/coder and my partner did the 
front-end coding. I did the database and object design, figured out how to use SQL with Java, and wrote all 
of the functions described in the Available Methods document. My partner did the rest of the coding. 

An overview of what is contained in the repo: 

	-  The "Project Guidelines" document is the project specifications as laid out by the professor

	-  The "RecipeManager" folder contains the NetBeans project

	-  The "Documentation" folder contains various use case, class, activity, and sequence diagrams as 
	   well as the Requirements Specification document. 

	-  The "Available methods" document contains a listing of all methods for database 
           interactions (the "SCRUD" - search, create, read, update, delete). This document was created 
	   mainly as a reference for the front-end coder. 

	-  The "Recipe Manager" powerpoint presentation file is what we used as part of our project 
	   presentation as required in the Project Guidelines document. All information in the powerpoint 
           is also in the Documentation folder. 

	-  The "RecipeDB.bak" file is a backup file of the Recipe database. It can be used to import the 
	   Recipe database into a SQL EXPRESS 2012 SP1 instance of Microsoft SQL Server. You will 
	   need to have the JDBC driver installed, have enabled TCP/IP on your SQLEXPRESS instance in the SQL
           Server Configuration Manager and also set the IPAll TCP port to 1433, and created a user with 
           appropriate server roles in SQL Server Management Studio. Update the connection string in all 
           three execSQL functions in RecipeManager.java with your user's login information (lines 1597, 
           1627, and 1672). Be sure that the Server authentication mode is set to "SQL Server and Windows
	   Authentication mode" 


