package de.schulprojekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RezeptZutat {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    private Artikel artikel;

    private int menge;
    private String einheit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }
}
