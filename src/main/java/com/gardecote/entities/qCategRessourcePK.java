package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */

public class qCategRessourcePK implements Serializable {

    private enumSegPeche segPeche;
    private enumCategRessource categorie;


    public qCategRessourcePK() { super();
    }

    public qCategRessourcePK(enumSegPeche segPeche, enumCategRessource categorie) {
        this.segPeche = segPeche;
        this.categorie = categorie;
    }

    public enumSegPeche getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(enumSegPeche segPeche) {
        this.segPeche = segPeche;
    }

    public enumCategRessource getCategorie() {
        return categorie;
    }

    public void setCategorie(enumCategRessource categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCategRessourcePK)) return false;

        qCategRessourcePK that = (qCategRessourcePK) o;

        if (!segPeche.equals(that.segPeche)) return false;
        return categorie.equals(that.categorie);

    }

    @Override
    public int hashCode() {
        int result = segPeche.hashCode();
        result = 31 * result + categorie.hashCode();
        return result;
    }
}
