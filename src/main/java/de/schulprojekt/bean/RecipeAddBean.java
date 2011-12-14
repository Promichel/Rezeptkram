package de.schulprojekt.bean;

import de.schulprojekt.entities.RecipeIngredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeAddBean implements Serializable {
    private String name;
    private List<RecipeIngredient> ingredients;
    private int persons;

    // Zutat Textboxen
    private String ingredientName;
    private int ingredientAmount;
    private String ingredientUnit;

    private String description;

    public RecipeAddBean() {
        ingredients = new ArrayList<RecipeIngredient>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
