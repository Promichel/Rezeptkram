package de.schulprojekt.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String text;
    private int personenAnzahl;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RecipeIngredient> zutaten;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<RecipeIngredient> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<RecipeIngredient> zutaten) {
        this.zutaten = zutaten;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getPersonenAnzahl() {
        return personenAnzahl;
    }

    public void setPersonenAnzahl(int personenAnzahl) {
        this.personenAnzahl = personenAnzahl;
    }
}
