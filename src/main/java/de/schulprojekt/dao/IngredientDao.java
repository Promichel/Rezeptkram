package de.schulprojekt.dao;


import de.schulprojekt.bean.DiscounterSearchBean;
import de.schulprojekt.bean.IngredientSearchBean;
import de.schulprojekt.entities.Ingredient;
import de.schulprojekt.entities.Discounter;

import java.util.List;

public interface IngredientDao {

    //Discounter Section
    public void insertDiscounter(Discounter discounter);

    public void updateDiscounter(Discounter discounter);

    public List<Discounter> selectAllDiscounter();

    public List<Discounter> selectAllDiscounter(DiscounterSearchBean searchBean);

    //Ingredient Section
    public void insertIngredient(Ingredient ingredient);

    public void updateIngredient(Ingredient ingredient);

    public Ingredient selectIngredient(int id);

    public List<Ingredient> selectIngredients();

    public List<Ingredient> selectIngredients(IngredientSearchBean searchBean);

}
