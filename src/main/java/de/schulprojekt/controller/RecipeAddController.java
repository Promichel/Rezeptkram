package de.schulprojekt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.schulprojekt.bean.RecipeAddBean;
import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;

public class RecipeAddController {

	@Autowired
	private RecipeDao dao;

	private RecipeAddBean rab;

	private Logger logger = LoggerFactory.getLogger(RecipeAddController.class);

	private List<SelectItem> units;

	public RecipeAddController() {
		logger.debug("Init RecipeController");

		rab = new RecipeAddBean();
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
			recipe.setName(rab.getName());
			recipe.setText(rab.getDescription());
			recipe.setPersonAmount(rab.getPersons());
			recipe.setIngredients(rab.getIngredients());
			dao.insertRecipe(recipe);
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_INFO, "", "Das Rezept wurde hinzugefügt")
			);
			logger.info("Add Recipe Success, clean Temps");
			rab = new RecipeAddBean();
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Beim Hinzufügen des Rezepts ist ein Fehler aufgetreten!")
			);
		}

		return null;
	}

	public String addIngredient() {

		if (rab.getIngredientAmount() != 0 && rab.getIngredientName() != null && !rab.getIngredientName().equals("")
				&& rab.getIngredientUnit() != null && !rab.getIngredientUnit().equals("")) {

			RecipeIngredient ingredient = new RecipeIngredient();
			ingredient.setAmount(rab.getIngredientAmount());
			ingredient.setUnit(rab.getIngredientUnit());

			Ingredient article = new Ingredient();
			article.setName(rab.getIngredientName());

			ingredient.setIngredient(article);

			rab.getIngredients().add(ingredient);

			rab.setIngredientAmount(0);
			rab.setIngredientName("");

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new
				FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Bitte füllen Sie alle nötigen Felder aus!")
			);
		}

		return null;
	}

	public List<SelectItem> getUnits() {
		return units;
	}

	public void setUnits(List<SelectItem> units) {
		this.units = units;
	}

	public RecipeAddBean getRab() {
		return rab;
	}

	public void setRab(RecipeAddBean rab) {
		this.rab = rab;
	}

}
