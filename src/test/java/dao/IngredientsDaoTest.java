package dao;

import de.schulprojekt.bean.DiscounterSearchBean;
import de.schulprojekt.dao.IngredientDao;
import de.schulprojekt.entities.Discounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class IngredientsDaoTest {

    @Autowired
    IngredientDao dao;

    private Logger logger = LoggerFactory.getLogger(IngredientsDaoTest.class);

    @Before
    public void tearUp() {

        logger.debug("Insert Testdata to IngredientDatabase");
        logger.debug("Create 2 Sample Discounter");

        Discounter disc1 = new Discounter();
        disc1.setDiscounterName("Aldi");
        Discounter disc2 = new Discounter();
        disc2.setDiscounterName("Lidl");

        logger.debug("Add created Discounters to Database");
        dao.insertDiscounter(disc1);
        dao.insertDiscounter(disc2);

    }

    @Test
    public void selectAllDiscounters() {

        List<Discounter> discounters = dao.selectAllDiscounter();
        assertTrue(discounters != null);

    }

    @Test
    public void selectSpecialDiscounter() {

        DiscounterSearchBean searchBean = new DiscounterSearchBean();
        searchBean.setDiscounterName("Aldi");
        
        List<Discounter> discounters = dao.selectAllDiscounter(searchBean);
        assertTrue(discounters != null);
        assertTrue(discounters.size() == 1);

    }
    
    public void selectCheapestIngredient() {
        
        String ingredientToSearch = "Tomaten";

        
    }

}
