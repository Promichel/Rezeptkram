package de.schulprojekt.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "ingredientDao")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class IngredientDaoImpl implements IngredientDao {

    private Logger logger = LoggerFactory.getLogger(IngredientDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public IngredientDaoImpl() {

        logger.debug("Init Ingredient Dao Impl");

    }



}
