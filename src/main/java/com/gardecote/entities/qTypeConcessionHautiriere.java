package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */

public class qTypeConcessionHautiriere extends qTypeConcession {
    private enumTypeConcessionHautiriere enumTypeConcessionHautiriere;
    private enumTypePecheHautiriere  enumTypePecheHautiriere;

    public qTypeConcessionHautiriere(com.gardecote.entities.enumTypeConcessionHautiriere enumTypeConcessionHautiriere, com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        this.enumTypeConcessionHautiriere = enumTypeConcessionHautiriere;
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }

    public com.gardecote.entities.enumTypeConcessionHautiriere getEnumTypeConcessionHautiriere() {
        return enumTypeConcessionHautiriere;
    }

    public void setEnumTypeConcessionHautiriere(com.gardecote.entities.enumTypeConcessionHautiriere enumTypeConcessionHautiriere) {
        this.enumTypeConcessionHautiriere = enumTypeConcessionHautiriere;
    }

    public com.gardecote.entities.enumTypePecheHautiriere getEnumTypePecheHautiriere() {
        return enumTypePecheHautiriere;
    }

    public void setEnumTypePecheHautiriere(com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }
}
