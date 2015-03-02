
package recipemanager;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeManager {
    public static void main(String[] args) {
        String[][] recipeIngredients = new String[][]{ 
            {"First ingredient", "1", "quant"}, 
            {"Second ingredient", "1", "quant"}
        };
        String[] recipeInstructions = {"Step one, do some stuff", 
                                       "Step two, do some more stuff", 
                                       "Step 3, do even more stuff"};
        
         Recipe myShinyNewRecipe;
 //     Recipe(String rn, int rate, int  cat,  int subcat, String prep, String[][] ingrd, String[] instruc)
        myShinyNewRecipe = new Recipe("NewRecipe", 5, 1, 1, "10 minutes", recipeIngredients, recipeInstructions);
         
         addRecipe(myShinyNewRecipe);
         
//            // Iterate through the data in the result set and display it.
//            while (rs.next())
//            {
//               System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)); 
//            }
    
    }
    public static boolean addRecipe(Recipe newRecipe) {
        //build insert statement for the recipe
        String thisRecipeId = "";
        
        String[] resultset = null;
        //check if an identical recipe exists
        String sqlGetRecipeId = "SELECT RecipeId from RecipeDB.dbo.Recipes where " + 
                             "RecipeName = '" + newRecipe.recipeName 
                         + "' and preptime = '" + newRecipe.preptime 
                         + "' and category = '" + newRecipe.category
                         + "' and subcategory = '" + newRecipe.subcategory
                         + "' and rating = '" + newRecipe.rating + "'";
        //System.out.println(sqlGetRecipeId);
         resultset = execSQLSingleColumnSelect(sqlGetRecipeId);
          
            if ( resultset.length > 0) { 
                //our query found a row! we don't want to insert it again.
                //System.out.println("Identified matching recipe");
            }
            else {
                //no data in our recordset, so the recipe does not exist
                //insert it now
                int result;
                String sqlInsertRecipe = "INSERT INTO RecipeDB.dbo.Recipes(RecipeName, PrepTime, "
                        + "Category, SubCategory, Rating) VALUES ('" + newRecipe.recipeName
                        + "','" + newRecipe.preptime
                        + "','" + newRecipe.category
                        + "','" + newRecipe.subcategory
                        + "','" + newRecipe.rating + "')";
                //System.out.println(sqlInsertRecipe);
                result = execSQLUpdateOrDelete(sqlInsertRecipe);
            }

        //get the id of our newly created recipe 
        //OR grab the ID of our existing identical recipe
        resultset = null;
        resultset = execSQLSingleColumnSelect(sqlGetRecipeId);
        thisRecipeId = resultset[0];
        System.out.println("The recipe id is " + thisRecipeId);
        //insert ingredients and instructions using the recipeid
        return true;
    }
    
    public static String[] execSQLSingleColumnSelect(String sqlStatement) {
        String[] result = null;
        ResultSet rs;
        //don't know how big our result will be,
        //use an ArrayList temporarily to hold the data
        ArrayList<String> myArrayList;
        myArrayList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement(); 
           // System.out.println("Executing...");
            rs = stmt.executeQuery(sqlStatement);
           // System.out.println("Done.");
            while (rs.next()) {
                myArrayList.add(rs.getString(1));
            }
            //move data to my handy-dandy regular array
            result = new String[myArrayList.size()];
            for (int i = 0; i < myArrayList.size(); i++) {
                result[i] = myArrayList.get(i);
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0); 
        }
        //can't return a resultset because the connection closes (and the 
        //result is lost) when you leave the method; so that's why I put 
        //it into an array
        return result;
    }
    
    public static int execSQLUpdateOrDelete(String sqlStatement) {
        int result = 0;
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
                Connection con = DriverManager.getConnection(connectionUrl);
                Statement stmt = con.createStatement(); 
                 //executeUpdate returns either 0 for no result, 
                 //or it returns the number of rows affected
                result = stmt.executeUpdate(sqlStatement);
            }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0); 
        }
        return result;
    }
}

class Recipe {
      String recipeName;
      int rating; 
      int category;
      int subcategory;
      String preptime;
      int RecipeID;
      String[][] ingredients; 
      String[] instructions;
      
      Recipe(){
          recipeName = "New recipe";
          rating = 3;
          category = 1;
          subcategory = 1;
          preptime = "Not specified";
      }
      
      Recipe(String rn, int rate, int  cat,  int subcat, String prep, String[][] ingrd, String[] instruc){
          recipeName= rn;
          rating=rate;
          category=cat;
          subcategory=subcat;
          preptime=prep;
          ingredients = ingrd;
          instructions = instruc;    
      }
    
}
