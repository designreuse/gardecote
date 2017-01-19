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

    private enumTypePecheHautiriere  enumTypePecheHautiriere;


    public qTypeConcessionHautiriere() {

    }


    public qTypeConcessionHautiriere(Integer qtypeconcessionpk,  qPrefix prefix, String designation, com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        super(qtypeconcessionpk,  prefix, designation);
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }

    public com.gardecote.entities.enumTypePecheHautiriere getEnumTypePecheHautiriere() {
        return enumTypePecheHautiriere;
    }

    public void setEnumTypePecheHautiriere(com.gardecote.entities.enumTypePecheHautiriere enumTypePecheHautiriere) {
        this.enumTypePecheHautiriere = enumTypePecheHautiriere;
    }


}
