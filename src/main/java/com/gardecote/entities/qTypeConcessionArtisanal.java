package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */
public class qTypeConcessionArtisanal extends qTypeConcession {
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
