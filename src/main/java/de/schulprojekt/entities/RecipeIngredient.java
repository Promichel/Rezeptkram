package de.schulprojekt.entities;

import javax.persistence.*;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Ingredient ingredient;

    private int amount;
    private String unit;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

		@Override
		public String toString() {
			return "RecipeIngredient [id=" + id + ", ingredient=" + ingredient + ", amount=" + amount + ", unit=" + unit + "]";
		}

}
