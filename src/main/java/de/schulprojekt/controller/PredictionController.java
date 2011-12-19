package de.schulprojekt.controller;

import de.schulprojekt.bean.IngredientListItem;
import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;
import de.schulprojekt.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.taglibs.facelets.SpringSecurityELLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 19.12.11
 * Time: 05:06
 */
public class PredictionController {

    private final Logger logger = LoggerFactory.getLogger(PredictionController.class);

    @Autowired
    RecipeDao dao;

    private List<Recipe> selectedRecipes;
    private List<IngredientListItem> ingredientList;

    public PredictionController() {
        logger.debug("Init PredictionController");
    }

    private void retrieveRecipes() {

        User logedInUser = SpringSecurityELLibrary.getUserDetails();
        if (logedInUser != null) {

            logger.debug("Retrieve predicted Recipes");
            Integer availableRecipes = dao.countAllRecipes();

            if (availableRecipes != null && availableRecipes != 0) {
                List<Recipe> recipes = dao.selectRecipes(14);
                if (recipes != null && recipes.size() > 0) {
                    selectedRecipes = recipes;
                    retrieveIngredientList();
                }
            }

        }

    }

    private void retrieveIngredientList() {

        logger.debug("Begin calculating Ingredient List");
        List<IngredientListItem> result = new ArrayList<IngredientListItem>();
        for (Recipe recipe : selectedRecipes) {

            List<RecipeIngredient> ingredients = recipe.getIngredients();
            for (RecipeIngredient ingredient : ingredients) {

                Ingredient ing = ingredient.getIngredient();
                IngredientListItem item = new IngredientListItem(ing.getName(), ingredient.getAmount());
                item.setUnit(ingredient.getUnit());

                result.add(item);
            }

        }
        ingredientList = result;

    }

    public String print() {

        return null;
    }

    public List<Recipe> getSelectedRecipes() {
        if (selectedRecipes == null) {
            retrieveRecipes();
        }
        return selectedRecipes;
    }

    public void setSelectedRecipes(List<Recipe> selectedRecipes) {
        this.selectedRecipes = selectedRecipes;
    }

    public List<IngredientListItem> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<IngredientListItem> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
