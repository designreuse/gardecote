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


    private enumTypePechCotiere enumTypePecheCotiere;





    public qTypeConcessionCotiere() {

    }

    public enumTypePechCotiere getEnumTypePecheCotiere() {
        return enumTypePecheCotiere;
    }

    public void setEnumTypePecheCotiere(enumTypePechCotiere enumTypePecheCotiere) {
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }


    public qTypeConcessionCotiere(Integer qtypeconcessionpk, qPrefix prefix, String designation, enumTypePechCotiere enumTypePecheCotiere) {
        super(qtypeconcessionpk, prefix, designation);
        this.enumTypePecheCotiere = enumTypePecheCotiere;
    }
}
