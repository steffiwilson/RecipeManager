
package recipemanager;
import java.sql.*;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import static java.sql.ResultSet.CONCUR_UPDATABLE;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeManager {
    public static void main(String[] args) {
        
        //Creating recipes
        //initialization for a test recipe
        String[][] recipeIngredients = new String[][]{ 
            //Ingredient, quantity, measurement (cups, tsp), alt ingredient 
            {"First ingredient", "1", "measure", "NULL"}, 
            {"Second ingredient", "1", "measure", "NULL"}
        };
        String[] recipeInstructions = {"Step one, do some stuff", 
                                       "Step two, do some more stuff", 
                                       "Step 3, do even more stuff"};
        Recipe myShinyNewRecipe;
        myShinyNewRecipe = new Recipe("NewRecipe", 5, 1, 1, "10 minutes", recipeIngredients, recipeInstructions);
        //end of rest recipe initialization, insert it!
       // addRecipe(myShinyNewRecipe);
        
        String[] editedRecipeInstructions = {"A brand-new first step",
                                             "A never-before-seen second step",
                                             "Whadda ya know, the third step is new too",
                                             "Oh golly gee there are four steps now?"};
        //Editing recipes
      //  editInstructions(2010, editedRecipeInstructions);
        
       // addComment(2010, "This is an awesome comment!");
       //listIngredients(2010);
// while (rs.next()) {  System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));  }

    }
    
    public static String[][] listAllRecipes() {
        String[][] Recipes = new String[][]{};
        String sqlListRecipes = "  use RecipeDB\n" +
        "  SELECT RecipeId, RecipeName, PrepTime, CategoryName, SubCategoryName, Rating\n" +
        "  FROM Recipes\n" +
        "  LEFT JOIN CodeCategory on Category = CategoryID\n" +
        "  LEFT JOIN CodeSubCategory on SubCategory = SubCategoryID";
        Recipes =  execSQLMultiColumnSelect(sqlListRecipes);
        
        return Recipes;
    }
    
    public static String[][] listIngredients(int recipeId) {
        String[][] Ingredients = new String[][]{};
        String sqlListIngredients = "SELECT Ingredient, Quantity, Measurement, AltIngredient FROM Ingredients WHERE RecipeId = '" + recipeId + "'";
        Ingredients = execSQLMultiColumnSelect(sqlListIngredients);
        
        //System.out.println(Arrays.deepToString(Ingredients));
        
        return Ingredients;
    }
 
    public static String[][] listInstructions(int recipeId) {
        String[][] Instructions = new String[][]{};
        String sqlListInstructions = "SELECT StepNumber, Step FROM Ingredients WHERE RecipeId = '" + recipeId + "'";
        Instructions = execSQLMultiColumnSelect(sqlListInstructions);
        return Instructions;
    }

    public static String[][] listComments(int recipeId) {
        String[][] Comments = new String[][]{};
        String sqlListComments = "SELECT Comment, Date FROM Comments WHERE RecipeId = '" + recipeId + "'";
        Comments = execSQLMultiColumnSelect(sqlListComments);
        return Comments;
    }

    public static String[][] listCategories() {
        String[][] Categories = new String[][]{};
        String sqlListCategories = "SELECT CategoryId, CategoryName FROM CodeCategory";
        Categories = execSQLMultiColumnSelect(sqlListCategories);
        return Categories;
    }
 
    public static String[][] listSubCategories() {
        String[][] subCategories = new String[][]{};
        String sqlListSubCategories = "SELECT CategoryId, CategoryName FROM CodeSubCategory";
        subCategories = execSQLMultiColumnSelect(sqlListSubCategories);
        return subCategories;
    }

    public static boolean addCategory(String category) {
        String sqlAddCategory = "INSERT INTO CodeCategory(CategoryName) VALUES( '" + category + "')";
        execSQLUpdateOrDelete(sqlAddCategory);
        return true;
    }
    
    public static boolean addSubCategory(String subCategory) {
        String sqlAddCategory = "INSERT INTO CodeCategory(CategoryName) VALUES( '" + subCategory + "')";
        execSQLUpdateOrDelete(sqlAddCategory);
        return true;
    }
    public static boolean deleteComments(int recipeId) {
        String sqlDeleteComments = "DELETE FROM Comments WHERE RecipeId = '" + recipeId + "'";
        execSQLUpdateOrDelete(sqlDeleteComments);
        return true;
    }
    public static boolean addComment(int recipeId, String comment) {
        Date now = new Date();
        //magic! don't ask. And DON'T change the capitalization D:
        SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");

        String sqlAddComment = "INSERT INTO Comments(RecipeId, Comment, Date) VALUES ( '"
                + recipeId + "', '" + comment + "', '" + sqlDate.format(now) + "')";
        execSQLUpdateOrDelete(sqlAddComment);
        return true;
    }
    
    public static boolean editInstructions(int recipeId, String[] recipeInstructions) {
        deleteInstructions(recipeId);
        insertInstructions(recipeId, recipeInstructions);
        return true;
    }
    
    public static boolean editIngredients(int recipeId, String[][] recipeIngredients) {
        deleteIngredients(recipeId);
        insertIngredients(recipeId, recipeIngredients);
        return true;
    }
    
    public static boolean editRecipe(Recipe editedRecipe) {
        String sqlUpdateRecipe = "UPDATE Recipes SET RecipeName = '" + editedRecipe.recipeName 
                         + "', PrepTime = '" + editedRecipe.preptime 
                         + "', Category = '" + editedRecipe.category 
                         + "', SubCategory = '" + editedRecipe.subcategory
                         + "', Rating = '" + editedRecipe.rating
                         + "' where RecipeId = '" + editedRecipe.RecipeID +"'";
        execSQLUpdateOrDelete(sqlUpdateRecipe);
        return true;
    }
    
    public static boolean insertInstructions(int recipeId, String[] recipeInstructions) {
        int numberOfInstructions = recipeInstructions.length;
        String sqlInsertRecipeInstructions = "";
        int stepNumber = 1;
        for (int i=0; i < numberOfInstructions; i++) {
            sqlInsertRecipeInstructions = "INSERT INTO RecipeDB.dbo.Instructions(RecipeId, "
                + "StepNumber, Step) VALUES ('" + recipeId + "', '"
                + stepNumber + "','" + recipeInstructions[i] + "')";
            execSQLUpdateOrDelete(sqlInsertRecipeInstructions);
            stepNumber++;
        }
        return true;
    }
    
    public static boolean insertIngredients(int recipeId, String[][] recipeIngredients) {
        int numberOfIngredients = recipeIngredients.length;
        String sqlInsertRecipeIngredients= "";
        for (int i=0; i < numberOfIngredients; i++ ) {
             sqlInsertRecipeIngredients = "INSERT INTO RecipeDB.dbo.Ingredients(RecipeID, Ingredient, "
                + "Quantity, Measurement, AltIngredient) VALUES ('" + recipeId + "', '"
                + recipeIngredients[i][0] + "', '" + recipeIngredients[i][1] + "', '"
                + recipeIngredients[i][2] + "', '" + recipeIngredients[i][3] + "')";
            execSQLUpdateOrDelete(sqlInsertRecipeIngredients);
        }
        return true;
    }

    public static boolean deleteInstructions(int recipeId) {
        String sqlDeleteInstructions = "DELETE FROM Instructions where RecipeId = '" + recipeId + "'";
        execSQLUpdateOrDelete(sqlDeleteInstructions);
        return true;
    }
    
    public static boolean deleteIngredients(int recipeId) {
        String sqlDeleteIngredients = "DELETE FROM Ingredients where RecipeId = '" + recipeId + "'";
        execSQLUpdateOrDelete(sqlDeleteIngredients);
        return true;
    }
    
    public static boolean totallyDeleteRecipe(int recipeId) {
        String sqlDeleteRecipe = "DELETE FROM Recipes where RecipeId = '" + recipeId + "'";
        execSQLUpdateOrDelete(sqlDeleteRecipe);
        deleteInstructions(recipeId);
        deleteIngredients(recipeId);
        deleteComments(recipeId);
        return true;
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
                //recipe does not exist yet, insert it now
                String sqlInsertRecipe = "INSERT INTO RecipeDB.dbo.Recipes(RecipeName, PrepTime, "
                        + "Category, SubCategory, Rating) VALUES ('" + newRecipe.recipeName
                        + "','" + newRecipe.preptime
                        + "','" + newRecipe.category
                        + "','" + newRecipe.subcategory
                        + "','" + newRecipe.rating + "')";
                //System.out.println(sqlInsertRecipe);
                execSQLUpdateOrDelete(sqlInsertRecipe);
            }

        //get the id of our newly created recipe 
        //OR grab the ID of our existing identical recipe
        resultset = null;
        resultset = execSQLSingleColumnSelect(sqlGetRecipeId);
        thisRecipeId = resultset[0];
        deleteIngredients(Integer.parseInt(thisRecipeId));
        deleteInstructions(Integer.parseInt(thisRecipeId));
        insertIngredients(Integer.parseInt(thisRecipeId), newRecipe.ingredients);
        insertInstructions(Integer.parseInt(thisRecipeId), newRecipe.instructions);

        return true;
    }
   //SQL EXECUTION FUNCTIONS// 
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
    ///////////////////////////////////////////////////////////////////
        public static String[][] execSQLMultiColumnSelect(String sqlStatement) {
        String[][] result = null;
        java.sql.ResultSet rs;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
            Connection con = DriverManager.getConnection(connectionUrl);
            
            //enabling scrolling will allow us to preview the size of the result set,
            //and then jump back to iterate through the result set
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlStatement);

           
            //this came from teh interwebs
            //http://stackoverflow.com/questions/20021139/converting-resultset-to-multidimensional-string-array
            int rowSize = 0;
            try {
                rs.last();
                rowSize = rs.getRow();
                rs.beforeFirst();
            }
            catch(Exception ex) {
                System.out.println("Failed to get row size: " + ex);
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnSize = rsmd.getColumnCount();
            
            result = new String[rowSize][columnSize];
            int i = 0;
            while(rs.next() && i < rowSize)
            {
                for(int j=0;j<columnSize;j++){
                    result[i][j] = rs.getString(j+1);
                }
                i++;                    
            }
            //end of code from teh interwebs
        }
        catch (Exception e){
            System.out.println("Error executing the SQL: " + e.getMessage());
            System.exit(0); 
        }
        return result;
    }
    /////////////////////////////////////////////////////////////////////////
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
    //END OF SQL EXECUTION FUNCTIONS//
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
/* public static boolean editSingleInstruction(int recipeId, int instructionStep, String Instruction) {
        String sqlUpdateIngredient = "UPDATE Instructions SET Step = '" + Instruction + "' WHERE RecipeId = '"
                   + recipeId + "' and StepNumber = '" + instructionStep + "'";
        System.out.println(sqlUpdateIngredient);
        execSQLUpdateOrDelete(sqlUpdateIngredient);
        return true;
    } */