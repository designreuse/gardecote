package com.gardecote.entities;

/**
 * Created by Dell on 08/11/2016.
 */
public class segUsines {
    private enumSegPeche segPeche;
    private boolean choix;

    public segUsines(enumSegPeche segPeche, boolean choix) {
        this.segPeche = segPeche;
        this.choix = choix;
    }

    public enumSegPeche getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(enumSegPeche segPeche) {
        this.segPeche = segPeche;
    }

    public boolean isChoix() {
        return choix;
    }

    public void setChoix(boolean choix) {
        this.choix = choix;
    }
}
