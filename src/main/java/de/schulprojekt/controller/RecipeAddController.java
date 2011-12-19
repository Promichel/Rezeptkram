package de.schulprojekt.controller;

import de.schulprojekt.bean.RecipeAddBean;
import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;
import de.schulprojekt.entities.User;
import de.schulprojekt.exceptions.ParserException;
import de.schulprojekt.model.parser.IParser;
import de.schulprojekt.parsers.chefkoch.ChefkochParser;
import de.schulprojekt.parsers.dasKochrezept.DasKochrezeptParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.taglibs.facelets.SpringSecurityELLibrary;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

public class RecipeAddController {

    @Autowired
    private RecipeDao dao;

    private RecipeAddBean rab;

    private Logger logger = LoggerFactory.getLogger(RecipeAddController.class);

    private int selectedParser;

    private List<SelectItem> units;
    private List<SelectItem> availableParsers;

    private IParser parser;
    private String parserUrl;
    private Recipe newRecipe;

    public RecipeAddController() {
        logger.debug("Init RecipeController");

        rab = new RecipeAddBean();
        newRecipe = new Recipe();
        newRecipe.setIngredients(new ArrayList<RecipeIngredient>());
        units = selectUnits();
    }

    private List<SelectItem> selectUnits() {

        List<SelectItem> zUnits = new ArrayList<SelectItem>();
        zUnits.add(new SelectItem("l", "Liter"));
        zUnits.add(new SelectItem("ml", "Milliliter"));

        return zUnits;
    }

    public void resetUserVars() {
        logger.debug("Reset all vars to default value");
        newRecipe = new Recipe();
        newRecipe.setIngredients(new ArrayList<RecipeIngredient>());
    }

    public void onParserSelect(AjaxBehaviorEvent event) {

        HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        String input = menu.getValue().toString();


        logger.debug("Selected parser with id {}", selectedParser);
        if (selectedParser != 0) {
            parseFromSite();
        }

    }

    public String startParser() {

        logger.debug("Start parsing");
        if (selectedParser != 0 && parser != null) {
            try {
                newRecipe = parser.fetchRecipe(parserUrl);
                logger.debug("Recipe name: {}", newRecipe.getName());
            } catch (ParserException e) {
                logger.error("An error occurred while parsing recipe with url {} from parser with url {}", new Object[]{parserUrl, selectedParser});
            }

        }

        return null;
    }

    private void parseFromSite() {

        logger.debug("Start selecting");

        parser = null;

        switch (selectedParser) {
            case 1:
                logger.debug("Set Chefkoch Parser");
                parser = new ChefkochParser();
                break;
            case 2:
                logger.debug("Set Das Kochrezept Parser");
                parser = new DasKochrezeptParser();
                break;
        }

    }

    private void retrieveAvailableParsers() {
        availableParsers = new ArrayList<SelectItem>();

        availableParsers.add(new SelectItem(1, "Chefkoch.de"));
        availableParsers.add(new SelectItem(2, "DasKochrezept.de"));

    }

    public String addRecipe() {
        try {
            Recipe recipe = new Recipe();
            recipe.setName(newRecipe.getName());
            recipe.setText(newRecipe.getText());
            recipe.setPersonAmount(newRecipe.getPersonAmount());
            recipe.setIngredients(newRecipe.getIngredients());

            //Get logged in User
            logger.debug("Read out User from Spring Security Context");
            User owner = SpringSecurityELLibrary.getUserDetails();
            recipe.setOwner(owner);

            dao.insertRecipe(recipe);
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_INFO, "", "Das Rezept wurde hinzugefügt")
            );
            logger.info("Add Recipe Success, clean Temps");
            rab = new RecipeAddBean();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Beim Hinzufügen des Rezepts ist ein Fehler aufgetreten!")
            );
        }

        newRecipe = new Recipe();
        return "listRecipe?faces-redirect=true";
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

            newRecipe.getIngredients().add(ingredient);

            rab.setIngredientAmount(0);
            rab.setIngredientName("");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Bitte füllen Sie alle nötigen Felder aus!")
            );
        }

        return null;
    }

    public Recipe getNewRecipe() {
        return newRecipe;
    }

    public void setNewRecipe(Recipe newRecipe) {
        this.newRecipe = newRecipe;
    }

    public String getParserUrl() {
        return parserUrl;
    }

    public void setParserUrl(String parserUrl) {
        this.parserUrl = parserUrl;
    }

    public int getSelectedParser() {
        return selectedParser;
    }

    public void setSelectedParser(int selectedParser) {
        this.selectedParser = selectedParser;
    }

    public List<SelectItem> getAvailableParsers() {
        if (availableParsers == null) {
            retrieveAvailableParsers();
        }
        return availableParsers;
    }

    public void setAvailableParsers(List<SelectItem> availableParsers) {
        this.availableParsers = availableParsers;
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
