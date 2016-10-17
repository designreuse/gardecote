package com.gardecote.entities;

/**
 * Created by Dell on 09/10/2016.
 */
public enum enumSegPeche {
    PA("Peche Artisanal"),
    PC("Pêche Cotier"),
    PH("Pêche Hautiriere");
    private String name;
    enumSegPeche(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

}
