package com.gardecote.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("THAUTIRIERE")
public class qTypeConcessionHautiriere extends qTypeConcession implements Serializable {
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


    public qTypeConcessionHautiriere() {

    }

    public qTypeConcessionHautiriere(enumPrefix prefixNum, com.gardecote.entities.enumTypeConcessionHautiriere enumTypeConcessionHautiriere, com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        super(prefixNum);
        this.enumTypeConcessionHautiriere = enumTypeConcessionHautiriere;
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }

    public com.gardecote.entities.enumTypePecheHautiriere getEnumTypePecheHautiriere() {
        return enumTypePecheHautiriere;
    }

    public void setEnumTypePecheHautiriere(com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }
}
