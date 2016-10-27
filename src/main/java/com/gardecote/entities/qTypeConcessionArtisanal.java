package com.gardecote.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("TARTISANAL")
public class qTypeConcessionArtisanal extends qTypeConcession implements Serializable {
    private enumTypeConcessionArtisanal enumTypeConcessionArtisanal;

    public qTypeConcessionArtisanal(com.gardecote.entities.enumTypeConcessionArtisanal enumTypeConcessionArtisanal) {
        this.enumTypeConcessionArtisanal = enumTypeConcessionArtisanal;
    }

    public com.gardecote.entities.enumTypeConcessionArtisanal getEnumTypeConcessionArtisanal() {
        return enumTypeConcessionArtisanal;
    }

    public void setEnumTypeConcessionArtisanal(com.gardecote.entities.enumTypeConcessionArtisanal enumTypeConcessionArtisanal) {
        this.enumTypeConcessionArtisanal = enumTypeConcessionArtisanal;
    }
}
