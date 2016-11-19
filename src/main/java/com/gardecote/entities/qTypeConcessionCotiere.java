package com.gardecote.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("TCOTIER")
public class qTypeConcessionCotiere extends qTypeConcession implements Serializable {

    private enumTypeConcessionCotiere enumTypeConcessionCotiere;
    private enumTypePechCotiere enumTypePecheCotiere;

    public qTypeConcessionCotiere(com.gardecote.entities.enumTypeConcessionCotiere enumTypeConcessionCotiere, enumTypePechCotiere enumTypePecheCotiere) {
        this.enumTypeConcessionCotiere = enumTypeConcessionCotiere;
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }



    public enumTypeConcessionCotiere getEnumSegPecheCotiere() {
        return enumTypeConcessionCotiere;
    }

    public void setEnumSegPecheCotiere(enumTypeConcessionCotiere enumSegPecheCotiere) {
        this.enumTypeConcessionCotiere = enumSegPecheCotiere;
    }

    public qTypeConcessionCotiere() {

    }

    public enumTypePechCotiere getEnumTypePecheCotiere() {
        return enumTypePecheCotiere;
    }

    public void setEnumTypePecheCotiere(enumTypePechCotiere enumTypePecheCotiere) {
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }

    public qTypeConcessionCotiere(Integer qtypeconcessionpk,enumPrefix prefixNum, com.gardecote.entities.enumTypeConcessionCotiere enumTypeConcessionCotiere, enumTypePechCotiere enumTypePecheCotiere) {
        super(qtypeconcessionpk,prefixNum);
        this.enumTypeConcessionCotiere = enumTypeConcessionCotiere;
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }

    @Override
    public String toString() {
        return  enumTypeConcessionCotiere.toString();
    }
}
