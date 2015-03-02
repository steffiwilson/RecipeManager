// Import the SQL Server JDBC Driver classes
import java.sql.*;

class ConnectionTest
{ 
       public static void main(String[] args)
       { 
       try 
       {
            // Load the SQLServerDriver class, build the
            // connection string, and get a connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;instance=SQLEXPRESS;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
            Connection con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected.");

            // Create and execute an SQL statement that returns some data. 
            String SQL = "SELECT RecipeID, RecipeName, PrepTime, Rating FROM Recipes"; 
            Statement stmt = con.createStatement(); 
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it. 
            while (rs.next()) 
            { 
               System.out.println("Recipe Name: " + rs.getString(2) + "\nPrep time: " + rs.getString(3) + "\nRating: " + rs.getString(4) + "\n\n"); 
            }

       } 
       catch(Exception e) 
       {
            System.out.println(e.getMessage());
            System.exit(0); 
       }
    }
}	