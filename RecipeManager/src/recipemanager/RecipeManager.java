
package recipemanager;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.sql.*;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import static java.sql.ResultSet.CONCUR_UPDATABLE;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RecipeManager extends javax.swing.JFrame{
    
    public RecipeManager(){
        initMainMenu();
    }
    
    public static void main(String[] args) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(ViewRecipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(ViewRecipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(ViewRecipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(ViewRecipe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        
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
        
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecipeManager().setVisible(true);
            }
        });


    }
     @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="initMainMenu">                          
    private void initMainMenu() {

        TitleLabel = new javax.swing.JLabel();
        AddRecipeButton = new javax.swing.JButton();
        SearchRecipesButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleLabel.setFont(new java.awt.Font("Stencil Std", 0, 48)); // NOI18N
        TitleLabel.setText("Recipe Manager");

        AddRecipeButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        AddRecipeButton.setText("Add Recipe");
        AddRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRecipeActionPerformed(evt);
            }
        });

        SearchRecipesButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        SearchRecipesButton.setText("Search Recipes");
        SearchRecipesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchRecipesActionPerformed(evt);
            }
        });

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
                       
    
    // <editor-fold defaultstate="collapsed" desc="Display GUI Code"> 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 77, Short.MAX_VALUE)
                .addComponent(TitleLabel)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddRecipeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchRecipesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(AddRecipeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(SearchRecipesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        // </editor-fold>                        


        pack();
    }// </editor-fold>                        

    // <editor-fold defaultstate="collapsed" desc="initViewRecipe">                          
    private void initViewRecipe(final int RecipeID) {

        AddCommentButton = new javax.swing.JButton();
        EditRecipeButton = new javax.swing.JButton();
        EmailRecipeButton = new javax.swing.JButton();
        deleteRecipeButton = new javax.swing.JButton();
        MainMenuButton = new javax.swing.JButton();
        RecipeNameLabel = new javax.swing.JLabel();
        MainCategoryLabel = new javax.swing.JLabel();
        SubCategoryLabel = new javax.swing.JLabel();
        ingredientListPane = new javax.swing.JScrollPane();
        IngredientsDisplayList = new javax.swing.JList();
        directionListPane = new javax.swing.JScrollPane();
        DirectionDisplayList = new javax.swing.JList();
        commentsListPane = new javax.swing.JScrollPane();
        CommentsList = new javax.swing.JList();
        PrepTimeLabel = new javax.swing.JLabel();
        RatingLabel = new javax.swing.JLabel();
        imgPanel = new javax.swing.JPanel();
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        AddCommentButton.setText("Add Comment");
        AddCommentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCommentActionPerformed(evt, RecipeID);
            }
        });

        EditRecipeButton.setText("Edit Recipe");
        EditRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRecipeActionPerformed(evt, RecipeID);
            }
        });

        EmailRecipeButton.setText("Email Recipe");
        EmailRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailRecipeActionPerformed(evt, RecipeID);
            }
        });

        deleteRecipeButton.setText("Delete Recipe");
        deleteRecipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRecipeActionPerformed(evt, RecipeID);
            }
        });
        
        MainMenuButton.setText("Main Menu");
        MainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuButtonActionPerformed(evt);
            }
        });

        RecipeNameLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        RecipeNameLabel.setText(selectSingleRecipe(RecipeID)[0]);

        MainCategoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        if(!(selectSingleRecipe(RecipeID)[2].matches("Unspecified")))
            MainCategoryLabel.setText(selectSingleRecipe(RecipeID)[2]);
        else 
            MainCategoryLabel.setText("");

        SubCategoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        if(!(selectSingleRecipe(RecipeID)[3].matches("Unspecified")))
            SubCategoryLabel.setText(selectSingleRecipe(RecipeID)[3]);
        else 
            SubCategoryLabel.setText("");

        
        IngredientsDisplayList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = getSingleIngredientString(RecipeID);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        IngredientsDisplayList.setEnabled(false);
        ingredientListPane.setViewportView(IngredientsDisplayList);

        DirectionDisplayList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = getSingleDirectionString(RecipeID);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        DirectionDisplayList.setEnabled(false);
        directionListPane.setViewportView(DirectionDisplayList);

        CommentsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = getSingleCommentString(RecipeID);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        CommentsList.setEnabled(false);
        commentsListPane.setViewportView(CommentsList);

        PrepTimeLabel.setText("Prep time: " + selectSingleRecipe(RecipeID)[1]);

        RatingLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        RatingLabel.setText("Rating: " + selectSingleRecipe(RecipeID)[4]);
                        
    
    // <editor-fold defaultstate="collapsed" desc="Display GUI Code"> 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commentsListPane)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ingredientListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(directionListPane))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(AddCommentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditRecipeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EmailRecipeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRecipeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MainCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PrepTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(RatingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(SubCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(RecipeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RecipeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MainCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(SubCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PrepTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RatingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredientListPane, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(directionListPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(commentsListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteRecipeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(MainMenuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmailRecipeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditRecipeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddCommentButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        // </editor-fold>                        
    
        pack();
    }// </editor-fold>        
    
    // recipeID is -1 if it's a new recipe, else it will act as an editRecipe
     // <editor-fold defaultstate="collapsed" desc="initAddRecipe">                             
    private void initAddRecipe(final int RecipeID) {
        
        stringDirectionList  = new ArrayList<String>();
        stringIngredientList = new ArrayList<String>();
        
        if(RecipeID > 0){
            String[] stringIngredient = getSingleIngredientString(RecipeID);
            List<String> wordList = Arrays.asList(stringIngredient);  

            for (String e : wordList)  
            {  
              stringIngredientList.add(e); 
            }  
            
            String[] stringDirection = getSingleDirectionString(RecipeID);
            List<String> wordList2 = Arrays.asList(stringDirection);  

            for (String e : wordList2)  
            {  
              stringDirectionList.add(e); 
            }  
            
            IngredientsOfLists = getArrayListIngredients(RecipeID);
            
        }
        else {
            IngredientsOfLists = new ArrayList<List<String>>();
        }

        
        
        addIngredientButton = new javax.swing.JButton();
        addDirectionButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        removeIngredientButton = new javax.swing.JButton();
        removeDirectionButton = new javax.swing.JButton();
        MainMenuButton = new javax.swing.JButton();
        //jComboBox1 = new javax.swing.JComboBox();
        categorySubBox = new javax.swing.JComboBox();
        categoryMainBox = new javax.swing.JComboBox();
        ingedientFractionComboBox = new javax.swing.JComboBox();

        ratingBox = new javax.swing.JComboBox();
        ingredientmessurementBox = new javax.swing.JComboBox();
        
        addRecipeLabel = new javax.swing.JLabel();
        errorBoxLabel = new javax.swing.JLabel();        
        ingredentLabel = new javax.swing.JLabel();
        directionsLabel = new javax.swing.JLabel(); 
        cookTimeLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        hourLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        
        hourSpinner = new javax.swing.JSpinner();
        minSpinner = new javax.swing.JSpinner();
        
        ingredientListPane = new javax.swing.JScrollPane();
        directionListPane = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        
        RecipeNameTextField = new javax.swing.JTextField();
        IngredentTextField = new javax.swing.JTextField();
        messurementTypeTextField = new javax.swing.JTextField();
        altIngredentTextField = new javax.swing.JTextField();


        directionsTextArea = new javax.swing.JTextArea();
        
        altIngredientCheckBox = new javax.swing.JCheckBox();
        
            currentDirectionsTextArea = new javax.swing.JTextArea();
            currentDirectionsTextArea.setColumns(20);
            currentDirectionsTextArea.setRows(5);
            
            
            currentIngredentsTextArea = new javax.swing.JTextArea();
            currentIngredentsTextArea.setColumns(20);
            currentIngredentsTextArea.setRows(5);
            

           altIngredientCheckBox.setText("Alternate"); 
        
        //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       String[][] subcategories = listSubCategories();
        String[] subcategories2 = new String[subcategories.length];
        for (int i=0; i < subcategories.length; i++){
            subcategories2[i] = subcategories[i][1];
        }
        categorySubBox.setModel(new javax.swing.DefaultComboBoxModel(subcategories2));
        
        addRecipeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        if(RecipeID < 0)
            addRecipeLabel.setText("Add Recipe");
        else
            addRecipeLabel.setText("Edit Recipe");

        errorBoxLabel.setText("");

        String[][] categories = listCategories();
        String[] categories2 = new String[categories.length];
        for (int i=0; i < categories.length; i++){
            categories2[i] = categories[i][1];
        }
        categoryMainBox.setModel(new javax.swing.DefaultComboBoxModel(categories2));

        
        ingredentLabel.setText("Ingredent");
        ingredentLabel.setToolTipText("");
        
        
        
        
        if(RecipeID < 0){
           currentIngredentsTextArea.setEnabled(false);
           currentDirectionsTextArea.setEnabled(false);
        }
        
        ingredientListPane.setViewportView(currentIngredentsTextArea);
        
        directionListPane.setViewportView(currentDirectionsTextArea);

        addIngredientButton.setText("+");
        addIngredientButton.setToolTipText("");
        addIngredientButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        addIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIngredientButtonActionPerformed(evt);
            }
        });
        
        removeIngredientButton.setText("-");
        removeIngredientButton.setToolTipText("");
        removeIngredientButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        removeIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeIngredientButtonActionPerformed(evt);
            }
        });

        removeDirectionButton.setText("-");
        removeDirectionButton.setToolTipText("");
        removeDirectionButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        removeDirectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDirectionButtonActionPerformed(evt);
            }
        });

        ingredientmessurementBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        ingedientFractionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1/8", "3/8", "1/4", "3/4", "1/3", "2/3", "1/2" }));


        messurementTypeTextField.setText("Measurement Type");


        IngredentTextField.setText("Enter Ingredient");
        altIngredentTextField.setText("Alt Ingredient");
        

        directionsLabel.setText("Directions");


        addDirectionButton.setText("+");
        addDirectionButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        addDirectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDirectionButtonActionPerformed(evt);
            }
        });

        

        cookTimeLabel.setText("Prep Time");

        ratingLabel.setText("Rating");

      
        ratingBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        if(RecipeID < 0){
            RecipeNameTextField.setText("Enter Recipe Name");
        }
        else {
            RecipeNameTextField.setText(selectSingleRecipe(RecipeID)[0]);
        }
        

        


        directionsTextArea.setColumns(20);
        directionsTextArea.setRows(5);
        directionsTextArea.setText("Enter Directions Here");
        jScrollPane3.setViewportView(directionsTextArea);
        
        if(RecipeID > 0){
            int hour = 0;
            int min = 0;
            String time = selectSingleRecipe(RecipeID)[1];
            String[] timeParts = time.split("\\s+");
            if(timeParts.length == 4)
            {
                hour = Integer.parseInt(timeParts[0]);
                min = Integer.parseInt(timeParts[2]);
            }
            else if(timeParts.length == 2){
                if(timeParts[1].matches("hour") || timeParts[1].matches("hours")){
                    hour = Integer.parseInt(timeParts[0]);
                }
                else
                {
                    min = Integer.parseInt(timeParts[0]);
                }
              }
            hourSpinner.setModel(new javax.swing.SpinnerNumberModel(hour, 0, 24, 1));
            minSpinner.setModel(new javax.swing.SpinnerNumberModel(min, 0, 60, 1));
        }
        else
        {
            hourSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));
            minSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
        }

        hourLabel.setText("Hr");

        minLabel.setText("Min");

        submitButton.setText("Submit Recipe");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt, RecipeID);
            }
        });
        
        MainMenuButton.setText("Main Menu");
        MainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuButtonActionPerformed(evt);
            }
        });
                            
    
    // <editor-fold defaultstate="collapsed" desc="Display GUI Code"> 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addRecipeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errorBoxLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addIngredientButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(altIngredientCheckBox)
                                    .addComponent(addDirectionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ingredientmessurementBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ingedientFractionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(messurementTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                        .addComponent(IngredentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(categoryMainBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(categorySubBox, 0, 76, Short.MAX_VALUE))
                                                .addComponent(directionsLabel)
                                                .addGroup(layout.createSequentialGroup()
                                                    
                                                    .addComponent(altIngredentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(cookTimeLabel)
                                                .addComponent(ingredentLabel)
                                                .addComponent(RecipeNameTextField))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ratingLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ratingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(hourLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(minSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(minLabel)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitButton)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(MainMenuButton)
                        .addGap(122, 122, 122)))
                
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ingredientListPane, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(directionListPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeIngredientButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeDirectionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addRecipeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorBoxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(RecipeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryMainBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categorySubBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ingredentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ingedientFractionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(messurementTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IngredentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ingredientmessurementBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ingredientListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                       // .addComponent(directionListPane)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(altIngredientCheckBox)
                            .addComponent(altIngredentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        
                        .addComponent(directionsLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(addDirectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cookTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourLabel)
                            .addComponent(minLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ratingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ratingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(submitButton)
                        .addGap(18, 18, 18)
                        .addComponent(MainMenuButton)
                        .addContainerGap(47, Short.MAX_VALUE))                        
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(directionListPane)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(removeDirectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
 // </editor-fold>                        

        
        errorBoxLabel.getAccessibleContext().setAccessibleDescription("");
           if(RecipeID > 0){
                updateIngredients();
                updateDirections();
            }
            else
            {
                currentIngredentsTextArea.setEnabled(false);
                currentDirectionsTextArea.setEnabled(false);
            }
        pack();
    }// </editor-fold>                        
    
    // <editor-fold defaultstate="collapsed" desc="initSearchRecipe">                          
    private void initSearchRecipe() {

        resultsScrollPane1 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        searchRecipesLabel = new javax.swing.JLabel();
        selectButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        MainMenuButton = new javax.swing.JButton();
        RecipeNameTextField = new javax.swing.JTextField();
        categoryMainBox = new javax.swing.JComboBox();
        categorySubBox = new javax.swing.JComboBox();
        ratingBox = new javax.swing.JComboBox();
        IngredentTextField = new javax.swing.JTextField();
        hourSpinner = new javax.swing.JSpinner();
        minSpinner = new javax.swing.JSpinner();
        minLabel = new javax.swing.JLabel();
        hourLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        prepTimeCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Recipe Name", "Preperation Time", "Main Category", "Sub Category", "Rating"
            }
        ));
        resultsScrollPane1.setViewportView(resultsTable);

        searchRecipesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchRecipesLabel.setText("Search Recipes");

        selectButton.setText("Select");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        
        MainMenuButton.setText("Main Menu");
        MainMenuButton.setToolTipText("");
        MainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuButtonActionPerformed(evt);
            }
        });
        

        RecipeNameTextField.setText("Recipe Name");
        
        prepTimeCheckBox.setText("Prep Time");

         String[][] categories = listCategories();
        String[] categories2 = new String[categories.length];
        categories2[0] = "";
        for (int i=1; i < categories.length; i++){
            categories2[i] = categories[i][1];
        }
        categoryMainBox.setModel(new javax.swing.DefaultComboBoxModel(categories2));


        String[][] subcategories = listSubCategories();
        String[] subcategories2 = new String[subcategories.length];
        subcategories2[0] = "";
        for (int i=1; i < subcategories.length; i++){
            subcategories2[i] = subcategories[i][1];
        }
        categorySubBox.setModel(new javax.swing.DefaultComboBoxModel(subcategories2));

        ratingBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5" }));

        hourSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));

        minSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        IngredentTextField.setText("Ingredient");

        minLabel.setText("minutes");

        hourLabel.setText("hours");

        ratingLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ratingLabel.setText("Rating");                      
    
    // <editor-fold defaultstate="collapsed" desc="Display GUI Code"> 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchRecipesLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(resultsScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IngredentTextField)
                            .addComponent(RecipeNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(categoryMainBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(categorySubBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ratingLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ratingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prepTimeCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hourLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(MainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchRecipesLabel)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RecipeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryMainBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categorySubBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ratingBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ratingLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IngredentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourLabel)
                            .addComponent(prepTimeCheckBox)
                            .addComponent(minLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(resultsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(MainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        // </editor-fold>                        
     

        selectButton.getAccessibleContext().setAccessibleName("selectionButton");
        searchButton.getAccessibleContext().setAccessibleName("searchButton");

        pack();
    }// </editor-fold>                        

     
     // <editor-fold defaultstate="collapsed" desc="MAIN MENU Action Preformed Functions"> 
    private void AddRecipeActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        getContentPane().removeAll();
        initAddRecipe(-1);
    }
     
     private void SearchRecipesActionPerformed(java.awt.event.ActionEvent evt) {                                         
         getContentPane().removeAll();
         initSearchRecipe();
    }
     
     private void ExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
         System.exit(0);
    }
      // </editor-fold> 
     
     // <editor-fold defaultstate="collapsed" desc="VIEW RECIPE  Functions"> 
     private String[] getSingleDirectionString(int RecipeID){
     String recipeInstructions[][] = listInstructions(RecipeID);
        String[] strings = new String[recipeInstructions.length];
        for(int i = 0; i < recipeInstructions.length; i++)
            strings[i] = recipeInstructions[i][1];
        
        return strings;
     }
     
     private ArrayList<List<String>> getArrayListIngredients(int RecipeID){
         ArrayList<List<String>> ingredients = new ArrayList<List<String>>();
         String recipeIngredients[][] = listIngredients(RecipeID);
         for(int i = 0; i < recipeIngredients.length; i++) {
         ArrayList<String> IngredientList = new ArrayList<String>();
                IngredientList.add(recipeIngredients[i][0]);
                IngredientList.add(recipeIngredients[i][1]);
                IngredientList.add(recipeIngredients[i][2]); 
                IngredientList.add(recipeIngredients[i][3]);
            ingredients.add(IngredientList);
         }
         return ingredients;
     }
     
     private String[] getSingleIngredientString(int RecipeID){
     String recipeIngredients[][] = listIngredients(RecipeID);
        String[] strings = new String[recipeIngredients.length];
        for(int i = 0; i < recipeIngredients.length; i++)
            strings[i] = recipeIngredients[i][1] + " " + recipeIngredients[i][2] + " " + recipeIngredients[i][0] + " or " + recipeIngredients[i][3];
        
        return strings;
     }
     
     private String[] getSingleCommentString(int RecipeID){
     String Comments[][] = listComments(RecipeID);
        String[] strings = new String[Comments.length];
        for(int i = 0; i < Comments.length; i++)
            strings[i] = Comments[i][0] + "(at " + Comments[i][1] + ")";
        
        return strings;
     }
     private void AddCommentActionPerformed(java.awt.event.ActionEvent evt, final int RecipeID) {                                         
        String input =  JOptionPane.showInputDialog(this 
       ,"What did you think?",JOptionPane.QUESTION_MESSAGE);
        addComment(RecipeID, input);
        
        CommentsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = getSingleCommentString(RecipeID);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        CommentsList.setEnabled(false);
        commentsListPane.setViewportView(CommentsList);
    }
     
     private void EditRecipeActionPerformed(java.awt.event.ActionEvent evt, int RecipeID) {                                         
        // Direction and ingredient list should already be loaded
        getContentPane().removeAll();
        initAddRecipe(RecipeID);
    }
     
     private void EmailRecipeActionPerformed(java.awt.event.ActionEvent evt, int RecipeID) {                                         
         try {
             emailRecipe(RecipeID);
         }
         catch (IOException ex) {
             
         }
    }
     
     private void deleteRecipeActionPerformed(java.awt.event.ActionEvent evt, int RecipeID) {                                         
        totallyDeleteRecipe(RecipeID);
        getContentPane().removeAll();
        initMainMenu();
    }
      // </editor-fold> 
     
     // <editor-fold defaultstate="collapsed" desc="ADD RECIPE Functions">
     private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
       if(!(((String)IngredentTextField.getText()).matches("Enter Ingredient")) && 
               (!(((String)ingredientmessurementBox.getSelectedItem()).matches("")) || !(((String)ingedientFractionComboBox.getSelectedItem()).matches("")))) {
            String messurementType = (String)messurementTypeTextField.getText();
            //checks to see if the user entered something in mesurement type and sets the string equal to an empty string if they left it alone
            if(((messurementType).matches("Measurement Type"))) 
                messurementType = "";
            
            String messuremnt = (String)ingredientmessurementBox.getSelectedItem() + " " + (String)ingedientFractionComboBox.getSelectedItem();
            ArrayList<String> IngredientList = new ArrayList<String>();
                IngredientList.add((String)IngredentTextField.getText());
                IngredientList.add(messuremnt);
                IngredientList.add(messurementType); 
                if(altIngredientCheckBox.isSelected()){
                   IngredientList.add((String)altIngredentTextField.getText()); 
                }
                else
                    IngredientList.add(null);
            IngredientsOfLists.add(IngredientList);
             if(altIngredientCheckBox.isSelected()){
                 stringIngredientList.add(messuremnt + " " + messurementType + " " + (String)IngredentTextField.getText() + " or " + (String)altIngredentTextField.getText());
             }
             else
                stringIngredientList.add(messuremnt + " " + messurementType + " " + (String)IngredentTextField.getText());
        }

       updateIngredients();
       IngredentTextField.setText("Enter Ingredient");
    }  
    
    private void removeIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(IngredientsOfLists.size() > 0){
            IngredientsOfLists.remove(IngredientsOfLists.size() - 1);
            stringIngredientList.remove(IngredientsOfLists.size());
        }
        updateIngredients();   
        
        if(IngredientsOfLists.size() <= 0)
            ingredientListPane.setViewportView(currentIngredentsTextArea);
    }  
      private void addDirectionButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(!((String)directionsTextArea.getText()).matches("Enter Directions Here")){
          stringDirectionList.add((String)directionsTextArea.getText());
        }

        updateDirections();     
        directionsTextArea.setText("Enter Directions Here");
        
    } 
    private void removeDirectionButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(stringDirectionList.size() > 0)
            stringDirectionList.remove(stringDirectionList.size() - 1);

        updateDirections();   
        
        if(stringDirectionList.size() <= 0)
            directionListPane.setViewportView(currentDirectionsTextArea);
    }  
    
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt, int RecipeID) {                                           
        // directionList; IngredientsOfLists; (String)ratingBox.getSelectedItem(); (String)RecipieNameTextField.getText()
        //(String)categoryMainBox.getSelectedItem(); (String)categorySubBox.getSelectedItem(); 
        //(String)hourSpinner.getValue(); (String)minSpinner.getValue()
        String cookTime = convertTime((int)hourSpinner.getValue(), (int)minSpinner.getValue());
        
        
        
        if((((String)RecipeNameTextField.getText()).matches("Enter Recipe Name")))        
            errorBoxLabel.setText("Enter a valid Recipe Name");

        else if(stringDirectionList.size() == 0)
            errorBoxLabel.setText("Enter some directions");
        else if(IngredientsOfLists.size() == 0)
            errorBoxLabel.setText("Enter some Ingredients");     
        else {
            //add the recipe
            System.out.println("in submit: " + RecipeID);
            String [][] ingredients = new String[IngredientsOfLists.size()][4];
            for(int i = 0; i < IngredientsOfLists.size(); i++){
                ingredients[i][0] = IngredientsOfLists.get(i).get(0);
                ingredients[i][1] = IngredientsOfLists.get(i).get(1);
                ingredients[i][2] = IngredientsOfLists.get(i).get(2);
                ingredients[i][3] = IngredientsOfLists.get(i).get(3);
            }


            String[] directions =  new String[stringDirectionList.size()];
            for(int i = 0; i < stringDirectionList.size(); i++)
                directions[i] = stringDirectionList.get(i);
        
        
            Recipe recipeToBeAdded;
            

            //String rn, int rate, int  cat,  int subcat, String prep, String[][] ingrd, String[] instruc
            recipeToBeAdded = new Recipe(RecipeID, (String)RecipeNameTextField.getText(), Integer.parseInt((String)ratingBox.getSelectedItem()),
                    getCategoryId((String)categoryMainBox.getSelectedItem()),getSubCategoryId((String)categorySubBox.getSelectedItem()),
                    cookTime, ingredients, directions);

            
            addRecipe(recipeToBeAdded);
            
            getContentPane().removeAll();
            initMainMenu();
            
        } //end add recipe
    }  
    private void MainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) { 
        getContentPane().removeAll();
        initMainMenu();
    }
    
    
    private void updateDirections(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : stringDirectionList) {
            listModel.addElement(s);
        }
        final JList<String> newDirectionsList = new JList<String>(listModel);
        Dimension preferredSize = new Dimension(currentDirectionsTextArea.getPreferredSize().width,200);
        newDirectionsList.setPreferredSize(preferredSize);
       /* ListSelectionListener directionsSelect = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                currentDirectionsTextArea.setText(newDirectionsList.getSelectedValue());
            }
        };*/
       // newDirectionsList.addListSelectionListener(directionsSelect);
        directionListPane.setViewportView(newDirectionsList);
       // newDirectionsList.setEnabled(false);
        
    }
    
    private void updateIngredients(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : stringIngredientList) {
            listModel.addElement(s);
        }
        final JList<String> newIngredientList = new JList<String>(listModel);
        Dimension preferredSize = new Dimension(currentIngredentsTextArea.getPreferredSize().width,200);
        newIngredientList.setPreferredSize(preferredSize);
        /*ListSelectionListener ingredentsSelect = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                currentIngredentsTextArea.setText(newIngredientList.getSelectedValue());
            }
        };
        newIngredientList.addListSelectionListener(ingredentsSelect);*/
        ingredientListPane.setViewportView(newIngredientList);
    }
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="SEARCH RECIPE Functions">
     private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) { 
        String recipeName = "";
        String prepTime = "";
        String rating = "";
        String ingredient = "";
        String mainCategory = "";
        String subCategory = "";
        if(!(((String)RecipeNameTextField.getText()).matches("Recipe Name")))
            recipeName = (String)RecipeNameTextField.getText();
        if(!(((String)IngredentTextField.getText()).matches("Ingredient")))
            ingredient = (String)IngredentTextField.getText();
        if(prepTimeCheckBox.isSelected())
            prepTime = convertTime((int)hourSpinner.getValue(), (int)minSpinner.getValue());
        if(!((String)categoryMainBox.getSelectedItem()).matches(""))
            System.out.print("main category convert here");
        if(!((String)categorySubBox.getSelectedItem()).matches(""))
            System.out.print("sub category convert here");
        
        rating = (String)ratingBox.getSelectedItem();
        
        //use this to call search function
        String searchParameters[] = {recipeName, prepTime, mainCategory, subCategory, rating, ingredient, ""};
        //call search function
        //returned 2D array { { RecipeId, RecipeName, PrepTime, CategoryName, SubCategoryName, Rating } }
        
        String returnedArray[][] = search(searchParameters);
        
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            returnedArray,
            new String [] {
                "Recipe ID", "Recipe Name", "Preperation Time", "Main Category", "Sub Category", "Rating"
            }
        ));
        
        resultsTable.removeColumn(resultsTable.getColumnModel().getColumn(0));
        
    }                                        

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) { 
        int selectedRecipeID;
        if(resultsTable.getRowCount() > 1){
            selectedRecipeID = Integer.parseInt((String)(resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 0)));
       //System.out.print(selectedRecipeID);
       
        String [] string = getSingleIngredientString(selectedRecipeID);
        for (String s : string) {
             stringIngredientList.add(s);
         }
        string = getSingleDirectionString(selectedRecipeID);
        for (String s : string) {
             stringDirectionList.add(s);
         }
         getContentPane().removeAll();
         initViewRecipe(selectedRecipeID);
        }
    }        
    // </editor-fold> 

   private String convertTime(int hour, int min){
        if(hour > 0)
            if(hour > 1)
                if(min > 0)
                    if(min > 1)
                        return hour + " hours and " + min + " minutes";
                    else
                        return hour + " hours and 1 minute";
                else              
                     return hour + " hours";
            else
                if(min > 0)
                    if(min > 1)
                        return "1 hour and " + min + " minutes";
                    else
                        return "1 hour and 1 minute";
                else              
                     return "1 hour";
        else
            if(min > 0)
                    if(min > 1)
                        return  min + " minutes";
                    else
                        return "1 minute";
                else              
                     return "none";
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
    
    // <editor-fold defaultstate="collapsed" desc="Database Query Functions"> 
   
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
        String sqlListSubCategories = "SELECT SubCategoryId, SubCategoryName FROM CodeSubCategory";
        subCategories = execSQLMultiColumnSelect(sqlListSubCategories);
        return subCategories;
    }
    
    public static int getCategoryId(String name) {
        int id;
        String[] results;
        String sqlSelectCategoryId = "SELECT CategoryId from CodeCategory where CategoryName = '" + name + "'";
        results = execSQLSingleColumnSelect(sqlSelectCategoryId);
        id = Integer.parseInt(results[0]);
        return id;
    }
    
     public static int getSubCategoryId(String name) {
        int id;
        String[] results;
        String sqlSelectCategoryId = "SELECT SubCategoryId from CodeSubCategory where SubCategoryName = '" + name + "'";
        results = execSQLSingleColumnSelect(sqlSelectCategoryId);
        id = Integer.parseInt(results[0]);
        return id;
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
        deleteInstructions(recipeId);
        deleteIngredients(recipeId);
        deleteComments(recipeId);
        String sqlDeleteRecipe = "DELETE FROM Recipes where RecipeId = '" + recipeId + "'";
        execSQLUpdateOrDelete(sqlDeleteRecipe);

        return true;
    }
    public static boolean addRecipe(Recipe newRecipe) {
        //build insert statement for the recipe
       // System.out.println(newRecipe.RecipeID);
        String thisRecipeId = "";
        boolean isNewRecipe = false;
        boolean existsInDatabase = false;
        
        if (newRecipe.RecipeID == -1) {
            isNewRecipe = true;
        }
        
        if (newRecipe.RecipeID == -1) { //User is CREATING a recipe
            String[] resultset = null;
            //check if an identical recipe exists
            String sqlGetRecipeId = "SELECT RecipeId from RecipeDB.dbo.Recipes where " + 
                                 "RecipeName = '" + newRecipe.recipeName 
                             + "' and preptime = '" + newRecipe.preptime 
                             + "' and category = '" + newRecipe.category
                             + "' and subcategory = '" + newRecipe.subcategory
                             + "' and rating = '" + newRecipe.rating + "'";
            System.out.println(sqlGetRecipeId);
             resultset = execSQLSingleColumnSelect(sqlGetRecipeId);
          
            if ( resultset.length > 0) { 
                //we found a matching recipe, so the new recipe is identical to an existing one
                existsInDatabase = true;
                isNewRecipe = false;
            }     
            if (isNewRecipe = true) {
                //recipe does not exist yet, insert it now
                String sqlInsertRecipe = "INSERT INTO RecipeDB.dbo.Recipes(RecipeName, PrepTime, "
                        + "Category, SubCategory, Rating) VALUES ('" + newRecipe.recipeName
                        + "','" + newRecipe.preptime
                        + "','" + newRecipe.category
                        + "','" + newRecipe.subcategory
                        + "','" + newRecipe.rating + "')";
                execSQLUpdateOrDelete(sqlInsertRecipe);
            }
            //get the Id of the newly created recipe OR 
            //get the Id of the matching recipe to update
            resultset = execSQLSingleColumnSelect(sqlGetRecipeId);
            thisRecipeId = resultset[0];
        }
        else { //User is EDITING a recipe
            thisRecipeId = Integer.toString(newRecipe.RecipeID);
            String sqlUpdateRecipe = "UPDATE RecipeDB.dbo.Recipes SET RecipeName = '" 
             + newRecipe.recipeName + "', PrepTime = '" + newRecipe.preptime 
             + "', Category = '" + newRecipe.category + "', SubCategory = '"
             + newRecipe.subcategory + "', Rating = '" + newRecipe.rating 
             + "' WHERE RecipeID = '" + thisRecipeId + "'";
             execSQLUpdateOrDelete(sqlUpdateRecipe);
        }
   
        deleteIngredients(Integer.parseInt(thisRecipeId));
        deleteInstructions(Integer.parseInt(thisRecipeId));
        insertIngredients(Integer.parseInt(thisRecipeId), newRecipe.ingredients);
        insertInstructions(Integer.parseInt(thisRecipeId), newRecipe.instructions);
        return true;
    }
    
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
                         + "INNER JOIN Ingredients ing on ing.RecipeId = r.RecipeId "
                         + "INNER JOIN Instructions ins on ins.RecipeId = r.RecipeId "
                         + "WHERE r.RecipeName like '%" + searchParameters[0] 
                         + "' and r.PrepTime like '%" + searchParameters[1]
                         + "' and cc.CategoryName like '%" + searchParameters[2]
                         + "' and cs.SubCategoryName like '%" + searchParameters[3]
                         + "' and r.Rating like '%" + searchParameters[4] 
                         + "' and ing.Ingredient like '%" + searchParameters[5]
                         + "' and ins.Step like '%" + searchParameters[6] + "'";
        //System.out.println(sqlSearch);
        result = execSQLMultiColumnSelect(sqlSearch);
       
        return result;           
    }
    // </editor-fold> 
    
   // <editor-fold defaultstate="collapsed" desc="SQL EXECUTION FUNCTIONS">
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
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="VARIABLES">  
    private javax.swing.JButton AddRecipeButton;
    private javax.swing.JButton SearchRecipesButton;
    private javax.swing.JButton ExitButton;
     private javax.swing.JButton MainMenuButton;
    private javax.swing.JLabel  TitleLabel;
    private javax.swing.JButton AddCommentButton;
    private javax.swing.JButton EditRecipeButton;
    private javax.swing.JButton EmailRecipeButton;
    private javax.swing.JButton deleteRecipeButton;
    private javax.swing.JLabel RecipeNameLabel;
    private javax.swing.JLabel MainCategoryLabel;
    private javax.swing.JLabel SubCategoryLabel;
    private javax.swing.JLabel PrepTimeLabel;
    private javax.swing.JLabel RatingLabel;
    private javax.swing.JList IngredientsDisplayList;
    private javax.swing.JList DirectionDisplayList;
    private javax.swing.JList CommentsList;
    private javax.swing.JScrollPane ingredientListPane;
    private javax.swing.JScrollPane directionListPane;
    private javax.swing.JScrollPane commentsListPane;
    private javax.swing.JPanel imgPanel;
    private javax.swing.JButton addIngredientButton;
    private javax.swing.JButton addDirectionButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton removeIngredientButton;
    private javax.swing.JButton removeDirectionButton;
    private javax.swing.JComboBox categorySubBox;
    private javax.swing.JComboBox categoryMainBox;
    private javax.swing.JComboBox ingedientFractionComboBox;
    private javax.swing.JComboBox ratingBox;
    private javax.swing.JComboBox ingredientmessurementBox;

    private javax.swing.JLabel addRecipeLabel;
    private javax.swing.JLabel errorBoxLabel;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JLabel ingredentLabel;
    private javax.swing.JLabel directionsLabel;
    private javax.swing.JLabel minLabel;
    private javax.swing.JLabel cookTimeLabel;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner hourSpinner;
    private javax.swing.JSpinner minSpinner;
    private javax.swing.JTextArea directionsTextArea;
    private javax.swing.JTextArea currentDirectionsTextArea;
    private javax.swing.JTextArea currentIngredentsTextArea;
    private javax.swing.JTextField RecipeNameTextField;
    private javax.swing.JTextField IngredentTextField;
    private javax.swing.JTextField messurementTypeTextField;
    private javax.swing.JTextField altIngredentTextField;
     private javax.swing.JButton selectButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JCheckBox prepTimeCheckBox;
    private javax.swing.JCheckBox altIngredientCheckBox;
    private javax.swing.JLabel searchRecipesLabel;
    private javax.swing.JScrollPane resultsScrollPane1;
    private javax.swing.JTable resultsTable;

    String selected = "";
    
    
    
    private ArrayList<String> stringIngredientList = new ArrayList<String>();
    private ArrayList<String> stringDirectionList = new ArrayList<String>();
    
    private List<List<String>> IngredientsOfLists = new ArrayList<List<String>>();
    
   
} // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="Recipe Class"> 
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
      
      Recipe(int id, String rn, int rate, int  cat,  int subcat, String prep, String[][] ingrd, String[] instruc){
          RecipeID = id;
          recipeName= rn;
          rating=rate;
          category=cat;
          subcategory=subcat;
          preptime=prep;
          ingredients = ingrd;
          instructions = instruc;    
      }
    
}// </editor-fold> 

