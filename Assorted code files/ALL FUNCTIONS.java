    public static String[][] search(String[] searchParameters) {
        //{name, preptime, category, subcategory, rating, ingredient, instruction}
        String[][] result = new String[][]{};
        for (int i=0; i<searchParameters.length; i++) {
            //add wildcard to all search params
            searchParameters[i] = searchParameters[i] + "%";
        }
        
        String sqlSearch = "SELECT distinct r.RecipeId, r.RecipeName, r.PrepTime, cc.CategoryName, cs.SubCategoryname, r.Rating "
                         + "FROM RECIPES r "
                         + "INNER JOIN CodeCategory cc on cc.CategoryId = r.Category "
                         + "INNER JOIN CodeSubCategory cs on cs.SubCategoryId = r.SubCategory "
                         + "LEFT JOIN Ingredients ing on ing.Ingredient like '" + searchParameters[5] + "' and ing.RecipeId = r.RecipeId "
                         + "LEFT JOIN Instructions ins on ins.Step like '%" + searchParameters[6] + "' and ins.RecipeId = r.RecipeId "
                         + "WHERE r.RecipeName like '%" + searchParameters[0] 
                         + "' and r.PrepTime like '" + searchParameters[1]
                         + "' and r.Category like '" + searchParameters[2]
                         + "' and r.SubCategory like '" + searchParameters[3]
                         + "' and r.Rating like '" + searchParameters[4] + "'";
        //System.out.println(sqlSearch);
        result = execSQLMultiColumnSelect(sqlSearch);
       
        return result;           
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
        String sqlListInstructions = "SELECT StepNumber, Step FROM Instructions WHERE RecipeId = '" + recipeId + "'";
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
    
    public static String[] selectSingleRecipe(int recipeId) {
        String[][] results;
        String sqlSelectRecipe = "SELECT r.RecipeName, r.PrepTime, CategoryName, SubCategoryName, r.Rating from Recipes r "
                                + "INNER JOIN CodeCategory on r.category = categoryid "
                                + "INNER JOIN CodeSubCategory on r.subcategory = subcategoryid "
                                + "WHERE r.recipeId = '" + recipeId + "'";
        results = execSQLMultiColumnSelect(sqlSelectRecipe);
        String[] singleArrayResult = new String[5];
        for (int i=0; i<5; i++) {
            singleArrayResult[i] = results[0][i];
        }
        return singleArrayResult;
    }
    
    public static boolean emailRecipe(int recipeId) throws IOException {
        String[] recipe = selectSingleRecipe(recipeId);
        String[][] instructions = listInstructions(recipeId);
        String[][] ingredients = listIngredients(recipeId);
        
        String emailBody = recipe[0] + "\n\nIngredients:\n";
        for (int i = 0; i < ingredients.length; i++) {
            emailBody = emailBody + ingredients[i][1] 
                            + " " + ingredients[i][2] 
                            + " " + ingredients[i][0] 
                            + "\n";
        }
        //clear up all the decimal points for readability
        emailBody = emailBody.replaceAll(".0", "");
        emailBody = emailBody.replaceAll(".25", "1%2F4"); //%2F = forward slash
        emailBody = emailBody.replaceAll(".5", "1%2F2");
        emailBody = emailBody.replaceAll(".75", "3%2F4");
        emailBody = emailBody.replaceAll(".33", "1%2F3");
        emailBody = emailBody.replaceAll(".66", "2%2F3");
        emailBody = emailBody.replaceAll("0", "");
        
        emailBody = emailBody + "\nInstructions:\n";
        for (int i = 0; i < instructions.length; i++){
            emailBody = emailBody + instructions[i][0] + ". " + instructions[i][1] + "\n";
        }
        emailBody = emailBody + "\nPrep Time: " + recipe[1] + "\nCategory: " 
                              + recipe[2] + "\nSubCategory: " + recipe[3]
                              + "\nRating: " + recipe[4];
       
        //mailto can't have spaces, so we replace spaces with %20
        emailBody = emailBody.replaceAll(" ", "%20");
        //replace the friendly java newline character with the friendly ASCII newline
        emailBody = emailBody.replaceAll("\n", "%0D");
       

        String uri = "mailto:?body=" + emailBody;
        System.out.println(uri);
        URI email = URI.create(uri);
        Desktop desktop = Desktop.getDesktop();
        desktop.mail(email);
        return true;
    }
    
    public static boolean searchAllRecipes(String searchKey) {
        //search request string: http://food2fork.com/api/search?key=64c27c4069bf215b87ebd1ede3ac32a0&q=[searchkey]&sort=r
        //single recipe request: http://food2fork.com/api/get?key=64c27c4069bf215b87ebd1ede3ac32a0&rId=[recipeIdfrompreviousresults]
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
            String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS2012;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
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
            String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS2012;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
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
            String connectionUrl = "jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS2012;databaseName=RecipeDB;user=RecipeManager;password=RecipeManagerPassword"; 
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