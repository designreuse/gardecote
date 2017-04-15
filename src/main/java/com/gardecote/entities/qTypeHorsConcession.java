package com.gardecote.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;
import javax.persistence.Id;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("THORSCONCESSION")
public class qTypeHorsConcession extends qTypeConcession  implements Serializable {

    private enumAncCategRessource enumAncCategRessource;

    public qTypeHorsConcession(com.gardecote.entities.enumAncCategRessource enumAncCategRessource) {
        this.enumAncCategRessource = enumAncCategRessource;
    }

    public com.gardecote.entities.enumAncCategRessource getEnumAncCategRessource() {
        return enumAncCategRessource;
    }

    public void setEnumAncCategRessource(com.gardecote.entities.enumAncCategRessource enumAncCategRessource) {
        this.enumAncCategRessource = enumAncCategRessource;
    }

    public qTypeHorsConcession() {
    }

}
