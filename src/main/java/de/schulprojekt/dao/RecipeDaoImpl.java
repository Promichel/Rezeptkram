package de.schulprojekt.dao;


import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.entities.Rezept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "recipeDao")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class RecipeDaoImpl implements RecipeDao {

    private Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insertRecipe(Rezept recipe) {

        logger.info("Add new Recipe to Database");
        em.persist(recipe);

    }

    @Transactional
    public void updateRecipe(Rezept recipe) {

        logger.info("Update Recipe with id: " + recipe.getId());
        em.merge(recipe);

    }

    @Transactional
    public Rezept selectRecipe(int id) {

        logger.info("Select Recipe with id: " + id);
        Query query = em.createQuery("select r from Rezept r where r.id = :id");
        query.setParameter("id", id);

        Rezept recipe = (Rezept) query.getSingleResult();
        logger.debug("Recipe has " + recipe.getZutaten().size() + " articles");

        return recipe;

    }

    public List<Rezept> selectRecipes(RecipeSearchBean searchBean) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int countSelectedRecipes(RecipeSearchBean searchBean) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
