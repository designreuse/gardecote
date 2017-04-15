package com.gardecote.web;

import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qTypeConcession;

/**
 * Created by Dell on 05/04/2017.
 */
public class choixTypeConcession {
    private Boolean choix;
    private qTypeConcession typeConcession;
    private qCategRessource categorie;

    public qCategRessource getCategorie() {
        return categorie;
    }

    public void setCategorie(qCategRessource categorie) {
        this.categorie = categorie;
    }

    public choixTypeConcession() {
    }

    public Boolean getChoix() {
        return choix;
    }

    public void setChoix(Boolean choix) {
        this.choix = choix;
    }

    public qTypeConcession getTypeConcession() {
        return typeConcession;
    }

    public void setTypeConcession(qTypeConcession typeConcession) {
        this.typeConcession = typeConcession;
    }
}
