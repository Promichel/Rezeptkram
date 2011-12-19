package de.schulprojekt.dao;


import de.schulprojekt.bean.RecipeSearchBean;
import de.schulprojekt.entities.Recipe;
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
    public void insertRecipe(Recipe recipe) {

        logger.info("Add new Recipe to Database");
        em.persist(recipe);

    }

    @Transactional
    public void updateRecipe(Recipe recipe) {

        logger.info("Update Recipe with id: " + recipe.getId());
        em.merge(recipe);

    }

    @Transactional
    public void deleteRecipe(Recipe recipe) {

        logger.info("Delete Recipte with id: " + recipe.getId());
        em.merge(recipe);
        em.remove(recipe);

    }

    @Transactional
    public Recipe selectRecipe(int id) {

        logger.info("Select Recipe with id: " + id);
        Query query = em.createQuery("select r from Recipe r where r.id = :id");
        query.setParameter("id", id);

        Recipe recipe = (Recipe) query.getSingleResult();
        logger.debug("Recipe has " + recipe.getIngredients().size() + " articles");

        return recipe;

    }

    public List<Recipe> selectRecipes(RecipeSearchBean searchBean) {

        logger.info("select Recipe with first: {} and pageSize: {}", new Object[]{searchBean.getFirst(), searchBean.getPageSize()});

        Query query = em.createQuery("select r from Recipe r");
        query.setFirstResult(searchBean.getFirst());
        query.setMaxResults(searchBean.getPageSize());

        List<Recipe> recipes = (List<Recipe>) query.getResultList();

        for (Recipe recipe : recipes) {
            //Make some cruel debug stuff :o)
            recipe.getIngredients().toString();
        }

        return recipes;
    }

    public int countSelectedRecipes(RecipeSearchBean searchBean) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional
    public void removeRecipe(Recipe selectedRecipe) {
        logger.info("Remove recipe: {} from database", selectedRecipe.toString());

        Recipe recipe = em.merge(selectedRecipe);
        em.remove(recipe);
    }

    @Override
    public Integer countAllRecipes() {
        Query result = em.createQuery("select count(r) from Recipe r");
        return ((Long) result.getSingleResult()).intValue();
    }

    @Override
    public List<Recipe> selectRecipes(int i) {

        Query query = em.createQuery("select r from Recipe r");
        query.setMaxResults(i);

        return query.getResultList();
    }

}
