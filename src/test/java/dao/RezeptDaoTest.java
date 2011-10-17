package dao;


import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Recipe;
import de.schulprojekt.entities.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RezeptDaoTest {

    @Autowired
    private RecipeDao dao;

    @Before
    public void insertRecipe() {

        Recipe recipe = new Recipe();
        recipe.setName("Testname");
        recipe.setPersonAmount(4);

        RecipeIngredient zutat1 = new RecipeIngredient();

        Ingredient artikel1 = new Ingredient();
        artikel1.setName("Wasser");

        List<RecipeIngredient> zutaten = new ArrayList<RecipeIngredient>();

        zutat1.setIngredient(artikel1);
        zutat1.setUnit("ml");
        zutat1.setAmount(400);

        zutaten.add(zutat1);

        recipe.setIngredients(zutaten);

        dao.insertRecipe(recipe);

    }

    @Test
    public void updateRecipe() {

        Recipe recipe = dao.selectRecipe(1);

        assertTrue(recipe != null);
        assertTrue(recipe.getPersonAmount() == 4);

        recipe.setPersonAmount(6);

        dao.updateRecipe(recipe);

        recipe = dao.selectRecipe(1);
        assertTrue(recipe != null);
        assertTrue("Personenanzahl ist nun 6", recipe.getPersonAmount() == 4);

    }

    @Test
    public void deleteRecipe() {

        Recipe recipe = dao.selectRecipe(1);
        assertTrue(recipe != null);


    }

    @Test
    public void selectRecipe() {

        Recipe recipe = dao.selectRecipe(1);
        assertTrue(recipe != null);
        assertTrue(recipe.getName().equals("Testname"));
        assertTrue(recipe.getPersonAmount() == 4);
        assertTrue(recipe.getIngredients().size() != 0);

    }


}
