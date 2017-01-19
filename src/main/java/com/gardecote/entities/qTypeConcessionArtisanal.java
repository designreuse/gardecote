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




    public qTypeConcessionArtisanal() {
    }

    public qTypeConcessionArtisanal(Integer qtypeconcessionpk,  qPrefix prefix, String designation) {
        super(qtypeconcessionpk,  prefix, designation);
    }
}
