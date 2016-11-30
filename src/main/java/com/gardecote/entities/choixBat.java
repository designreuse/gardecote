package com.gardecote.entities;

/**
 * Created by Dell on 20/11/2016.
 */
public class choixBat {
    private Integer id;
    private String libelle;

    public choixBat() {
    }

    public choixBat(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
