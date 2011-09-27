package dao;


import de.schulprojekt.dao.RecipeDao;
import de.schulprojekt.dao.RecipeDaoImpl;
import de.schulprojekt.entities.Artikel;
import de.schulprojekt.entities.Rezept;
import de.schulprojekt.entities.RezeptZutat;
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

        Rezept recipe = new Rezept();
        recipe.setName("Testname");
        recipe.setPersonenAnzahl(4);

        RezeptZutat zutat1 = new RezeptZutat();

        Artikel artikel1 = new Artikel();
        artikel1.setName("Wasser");

        List<RezeptZutat> zutaten = new ArrayList<RezeptZutat>();

        zutat1.setArtikel(artikel1);
        zutat1.setEinheit("ml");
        zutat1.setMenge(400);

        zutaten.add(zutat1);

        recipe.setZutaten(zutaten);

        dao.insertRecipe(recipe);

    }

    @Test
    public void selectRecipe() {

        Rezept recipe = dao.selectRecipe(1);
        assertTrue(recipe != null);
        assertTrue(recipe.getName().equals("Testname"));
        assertTrue(recipe.getPersonenAnzahl() == 4);
        assertTrue(recipe.getZutaten().size() != 0);

    }


}
