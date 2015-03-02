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