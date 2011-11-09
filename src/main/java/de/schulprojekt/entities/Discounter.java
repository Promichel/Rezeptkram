package de.schulprojekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discounter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String discounterName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscounterName() {
        return discounterName;
    }

    public void setDiscounterName(String discounterName) {
        this.discounterName = discounterName;
    }
    
    @Override
    public String toString() {
    	return "id='" + this.id + "' | name='" + this.discounterName + "'";
    }
}
