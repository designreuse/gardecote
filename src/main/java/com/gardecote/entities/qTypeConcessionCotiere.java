package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */
public class qTypeConcessionCotiere extends qTypeConcession {

    private enumTypeConcessionCotiere enumTypeConcessionCotiere;
    private enumTypePechCotiere enumTypePecheCotiere;

    public enumTypeConcessionCotiere getEnumSegPecheCotiere() {
        return enumTypeConcessionCotiere;
    }

    public void setEnumSegPecheCotiere(enumTypeConcessionCotiere enumSegPecheCotiere) {
        this.enumTypeConcessionCotiere = enumSegPecheCotiere;
    }

    public enumTypePechCotiere getEnumTypePecheCotiere() {
        return enumTypePecheCotiere;
    }

    public void setEnumTypePecheCotiere(enumTypePechCotiere enumTypePecheCotiere) {
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }

}
