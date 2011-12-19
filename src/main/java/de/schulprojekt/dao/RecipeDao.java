package de.schulprojekt.dao;


import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.entities.Recipe;

import java.util.List;

public interface RecipeDao {

    public void insertRecipe(Recipe recipe);

    public void updateRecipe(Recipe recipe);

    public void deleteRecipe(Recipe recipe);

    public Recipe selectRecipe(int id);

    public List<Recipe> selectRecipes(RecipeSearchBean searchBean);

    public int countSelectedRecipes(RecipeSearchBean searchBean);

    void removeRecipe(Recipe selectedRecipe);
}
