package de.schulprojekt.entities;

import javax.persistence.*;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Ingredient ingredient;

    private float amount;
    private String unit;
    private String memberOf = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }

    public String getMemberOf() {
        return this.memberOf;
    }

    @Override
    public String toString() {
        return "RecipeIngredient [id=" + id + ", ingredient=" + ingredient + ", amount=" + amount + ", unit=" + unit + "]";
    }

}
