package de.schulprojekt.dao;


import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.entities.Rezept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {

    private Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public void insertRecipe(Rezept recipe) {

        logger.info("Add new Recipe to Database");
        em.persist(recipe);

    }

    public void updateRecipe(Rezept recipe) {

        logger.info("Update Recipe with id: " + recipe.getId());
        em.merge(recipe);

    }

    public Rezept selectRecipe(int id) {

        logger.info("Select Recipe with id: " + id);
        Query query = em.createQuery("select r from Rezept r where r.id = :id");
        query.setParameter("id", id);

        return (Rezept) query.getSingleResult();

    }

    public List<Rezept> selectRecipes(RecipeSearchBean searchBean) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int countSelectedRecipes(RecipeSearchBean searchBean) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
