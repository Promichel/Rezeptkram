package de.schulprojekt.dao;


import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.entities.Rezept;

import java.util.List;

public interface RecipeDao {

    public void insertRecipe(Rezept recipe);
    public void updateRecipe(Rezept recipe);

    public Rezept selectRecipe(int id);
    public List<Rezept> selectRecipes(RecipeSearchBean searchBean);
    public int countSelectedRecipes(RecipeSearchBean searchBean);

}
