package de.schulprojekt.dao;


import de.schulprojekt.bean.DiscounterSearchBean;
import de.schulprojekt.bean.IngredientSearchBean;
import de.schulprojekt.entities.Artikel;
import de.schulprojekt.entities.Discounter;

import java.util.List;

public interface IngredientDao {

    //Discounter Section
    public void insertDiscounter(Discounter discounter);
    public void updateDiscounter(Discounter discounter);
    public List<Discounter> selectAllDiscounter();
    public List<Discounter> selectAllDiscounter(DiscounterSearchBean searchBean);

    //Ingredient Section
    public void insertIngredient(Artikel ingredient);
    public void updateIngredient(Artikel ingredient);
    public Artikel selectIngredient(int id);
    public List<Artikel> selectIngredients();
    public List<Artikel> selectIngredients(IngredientSearchBean searchBean);
                         
}
