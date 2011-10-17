package de.schulprojekt.entities;

import javax.persistence.*;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int price;

    @OneToOne
    private Discounter discounter;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Discounter getDiscounter() {
        return discounter;
    }

    public void setDiscounter(Discounter discounter) {
        this.discounter = discounter;
    }
}
