package de.schulprojekt.dao;

import de.schulprojekt.bean.DiscounterSearchBean;
import de.schulprojekt.bean.IngredientSearchBean;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Discounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "ingredientDao")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class IngredientDaoImpl implements IngredientDao {

    private Logger logger = LoggerFactory.getLogger(IngredientDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public IngredientDaoImpl() {

        logger.debug("Init Ingredient Dao Impl");

    }

    @Override
    @Transactional
    public void insertDiscounter(Discounter discounter) {

        logger.info("Insert Discounter to database");
        em.persist(discounter);

    }

    @Override
    public void updateDiscounter(Discounter discounter) {

        logger.info("Update existing Discounter");
        em.persist(discounter);

    }

    @Override
    public List<Discounter> selectAllDiscounter() {

        logger.debug("Select all Discounter from Database");
        List<Discounter> resultList = null;

        try {

            Query query = em.createQuery("select d from Discounter d");
            resultList = query.getResultList();

        } catch (NoResultException e) {
            logger.info("No result were found!", e);
        }

        return resultList;
    }

    @Override
    public List<Discounter> selectAllDiscounter(DiscounterSearchBean searchBean) {

        logger.debug("Select Disconter with Filter");

        List<Discounter> discounters = null;

        try {

            Query query = em.createQuery("select d from Discounter d where (:discounterName is null or d.discounterName = :discounterName)");
            query.setParameter("discounterName", searchBean.getDiscounterName());

            discounters = query.getResultList();

        } catch (NoResultException e) {
            logger.info("No result were found", e);
        }

        return discounters;
    }

    @Override
    @Transactional
    public void insertIngredient(Ingredient ingredient) {

        logger.info("Insert new Ingredient to Database");
        em.persist(ingredient);

    }

    @Override
    public void updateIngredient(Ingredient ingredient) {

        logger.info("Update Ingredient at Database");
        em.merge(ingredient);

    }

    @Override
    public Ingredient selectIngredient(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Ingredient> selectIngredients() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Ingredient> selectIngredients(IngredientSearchBean searchBean) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
