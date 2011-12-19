package de.schulprojekt.controller;

import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.taglibs.facelets.SpringSecurityELLibrary;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 18.12.11
 * Time: 16:14
 */
public class RecipeInfoController {

    private final Logger logger = LoggerFactory.getLogger(RecipeInfoController.class);

    @Autowired
    RecipeDao dao;

    private Integer selectedRecipeId;
    private Recipe selectedRecipe;

    public RecipeInfoController() {

        logger.debug("Init RecipeInfoController");

    }

    public void initRecipe() {

        logger.debug("Init Recipe got called from Frontend");
        if (selectedRecipeId != null && selectedRecipeId != 0) {
            logger.debug("Parameter found! Start reading out from database");
            retrieveRecipeInfo();
        } else {
            logger.warn("No Parameter were given!");
        }

    }

    private void retrieveRecipeInfo() {

        logger.info("Read out Recipe with id {}", selectedRecipeId);
        selectedRecipe = dao.selectRecipe(selectedRecipeId.intValue());

    }

    public String rememberRecipe() {

        logger.debug("Put Recipe on User Remember List");


        return null;
    }

    public String deleteRecipe() {

        selectedRecipe = dao.selectRecipe(selectedRecipeId);

        if (selectedRecipe != null) {

            logger.debug("Check if Recipe was created by logged in User");
            User logedinUser = SpringSecurityELLibrary.getUserDetails();
            logger.debug("Logged In User Information: {}", logedinUser.toString());
            if (selectedRecipe.getOwner().getId().equals(logedinUser.getId())) {
                logger.debug("Remove Recipe from Database");
                dao.removeRecipe(selectedRecipe);
            }
        }

        return "listRecipe?faces-redirect=true";
    }

    public void setSelectedRecipeId(Integer selectedRecipeId) {
        this.selectedRecipeId = selectedRecipeId;
    }

    public Integer getSelectedRecipeId() {
        return selectedRecipeId;
    }

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }
}
