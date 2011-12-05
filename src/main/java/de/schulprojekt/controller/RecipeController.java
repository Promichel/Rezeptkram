package de.schulprojekt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;

public class RecipeController {

	@Autowired
	private RecipeDao dao;

	private Logger logger = LoggerFactory.getLogger(RecipeController.class);

	private String recipeName;
	private List<RecipeIngredient> ingredients;
	private int personen;

	// Zutat Textboxen
	private String ingredientName;
	private int ingredientAmount;
	private String ingredientUnit;

	private String recipeDescription;

	private List<SelectItem> units;

	public RecipeController() {
		logger.debug("Init RecipeController");

		ingredients = new ArrayList<RecipeIngredient>();
		units = selectUnits();
	}

	private List<SelectItem> selectUnits() {

		List<SelectItem> zUnits = new ArrayList<SelectItem>();
		zUnits.add(new SelectItem("l", "Liter"));
		zUnits.add(new SelectItem("ml", "Milliliter"));

		return zUnits;
	}

	public String addRecipe() {
		try {
			Recipe recipe = new Recipe();
			recipe.setName(recipeName);
			recipe.setText(recipeDescription);
			recipe.setPersonAmount(personen);
			recipe.setIngredients(ingredients);
			dao.insertRecipe(recipe);
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_INFO, "", "Das Rezept wurde hinzugefügt")
			);
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Beim Hinzufügen des Rezepts ist ein Fehler aufgetreten!")
			);
		}

		return null;
	}

	public String addIngredient() {

		if (ingredientAmount != 0 && ingredientName != null && !ingredientName.equals("")
				&& ingredientUnit != null && !ingredientUnit.equals("")) {

			RecipeIngredient ingredient = new RecipeIngredient();
			ingredient.setAmount(ingredientAmount);
			ingredient.setUnit(ingredientUnit);

			Ingredient article = new Ingredient();
			article.setName(ingredientName);

			ingredient.setIngredient(article);

			ingredients.add(ingredient);

			ingredientAmount = 0;
			ingredientName = "";

		} else {
			// todo: implement!
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Bitte füllen Sie alle nötigen Felder aus!")
			);
		}

		return null;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getIngredientAmount() {
		return this.ingredientAmount;
	}

	public void setIngredientAmount(int ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public String getRecipeDescription() {
		return recipeDescription;
	}

	public void setRecipeDescription(String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	public int getPersonen() {
		return personen;
	}

	public void setPersonen(int personen) {
		this.personen = personen;
	}

	public String getIngredientUnit() {
		return ingredientUnit;
	}

	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}

	public List<SelectItem> getUnits() {
		return units;
	}

	public void setUnits(List<SelectItem> units) {
		this.units = units;
	}
}
