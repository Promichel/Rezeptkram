package de.schulprojekt.controller;

import de.schulprojekt.entities.Artikel;
import de.schulprojekt.entities.RecipeIngredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1H37
 * Date: 19.09.11
 * Time: 08:55
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@SessionScoped
public class RecipeController {

    private Logger logger = LoggerFactory.getLogger(RecipeController.class);

    private String rezeptName;
    private List<RecipeIngredient> zutaten;
    private int personen;

    //Zutat Textboxen
    private String zutatName;
    private int zutatMenge;
    private String zutatEinheit;

    private String rezeptAnleitung;

    private List<SelectItem> einheiten;

    public RecipeController() {
        logger.debug("Init RecipeController");

        zutaten = new ArrayList<RecipeIngredient>();
        einheiten = selectEinheiten();
    }

    private List<SelectItem> selectEinheiten() {

        List<SelectItem> zEinheiten = new ArrayList<SelectItem>();
        zEinheiten.add(new SelectItem("l", "Liter"));
        zEinheiten.add(new SelectItem("ml", "Milliliter"));

        return zEinheiten;
    }

    public String addRezept() {

        if(zutatMenge != 0 && zutatName != null && !zutatName.equals("") && zutatEinheit != null && !zutatEinheit.equals("")) {

            RecipeIngredient zutat = new RecipeIngredient();
            zutat.setMenge(zutatMenge);
            zutat.setEinheit(zutatEinheit);
            Artikel artikel = new Artikel();
            artikel.setName(zutatName);

            zutat.setArtikel(artikel);

            zutaten.add(zutat);

            zutatMenge = 0;
            zutatName = "";

        }
        else {
            //todo: implement!
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Fehler!", "Bitte füllen Sie alle nötigen Felder aus!"));
        }

        return null;
    }



    public String getRezeptName() {
        return rezeptName;
    }

    public void setRezeptName(String rezeptName) {
        this.rezeptName = rezeptName;
    }

    public List<RecipeIngredient> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<RecipeIngredient> zutaten) {
        this.zutaten = zutaten;
    }

    public String getZutatName() {
        return zutatName;
    }

    public void setZutatName(String zutatName) {
        this.zutatName = zutatName;
    }

    public int getZutatMenge() {
        return zutatMenge;
    }

    public void setZutatMenge(int zutatMenge) {
        this.zutatMenge = zutatMenge;
    }

    public String getRezeptAnleitung() {
        return rezeptAnleitung;
    }

    public void setRezeptAnleitung(String rezeptAnleitung) {
        this.rezeptAnleitung = rezeptAnleitung;
    }

    public int getPersonen() {
        return personen;
    }

    public void setPersonen(int personen) {
        this.personen = personen;
    }

    public String getZutatEinheit() {
        return zutatEinheit;
    }

    public void setZutatEinheit(String zutatEinheit) {
        this.zutatEinheit = zutatEinheit;
    }

    public List<SelectItem> getEinheiten() {
        return einheiten;
    }

    public void setEinheiten(List<SelectItem> einheiten) {
        this.einheiten = einheiten;
    }
}
