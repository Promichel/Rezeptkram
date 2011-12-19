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
    private int personAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RecipeIngredient> ingredients;

    @OneToOne
    private User owner;

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

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPersonAmount() {
        return personAmount;
    }

    public void setPersonAmount(int personAmount) {
        this.personAmount = personAmount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", personAmount=" + personAmount +
                ", ingredients=" + ingredients +
                ", owner=" + owner +
                '}';
    }
}
