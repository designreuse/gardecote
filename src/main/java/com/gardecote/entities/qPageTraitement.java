package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("PTRAITEMENT")
@NamedQueries ( {
        @NamedQuery ( name="qPageTraitement.countAll", query="SELECT COUNT(x) FROM qPageTraitement x" )
} )

public class qPageTraitement extends qPageCarnet implements Serializable {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------

    @OneToOne
    private qTraitement     qtraitement;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy = "pagesTraitement",targetEntity = qOpTraitement.class)
    private List<qOpTraitement> opTraitements;
    //----------------------------------------------------------------------

   public qPageTraitement() {
   }

    public qPageTraitement(String numeroPage,Long numeroOrdrePage, enumEtatPage etat, qCarnet carnet, qTraitement qtraitement, List<qOpTraitement> opTraitements) {
        super(numeroPage,  carnet,numeroOrdrePage,etat);

        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }

    public qTraitement getQtraitement() {
        return qtraitement;
    }

    public void setQtraitement(qTraitement qtraitement) {
        this.qtraitement = qtraitement;
    }

    public List<qOpTraitement> getOpTraitements() {
        return opTraitements;
    }

    public void setOpTraitements(List<qOpTraitement> opTraitements) {
        this.opTraitements = opTraitements;
    }




}
