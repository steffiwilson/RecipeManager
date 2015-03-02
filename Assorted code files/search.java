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
                         + "LEFT JOIN Ingredients ing on ing.Ingredient like '" + searchParameters[5] + "' "
                         + "LEFT JOIN Instructions ins on ins.Step like '%" + searchParameters[6] + "' "
                         + "WHERE r.RecipeName like '%" + searchParameters[0] 
                         + "' and r.PrepTime like '" + searchParameters[1]
                         + "' and r.Category like '" + searchParameters[2]
                         + "' and r.SubCategory like '" + searchParameters[3]
                         + "' and r.Rating like '" + searchParameters[4] + "'";
        //System.out.println(sqlSearch);
        result = execSQLMultiColumnSelect(sqlSearch);
       
        return result;           
    }