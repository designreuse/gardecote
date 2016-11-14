package com.gardecote.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@DiscriminatorValue("PTRAITEMENT")
@NamedQueries ( {
        @NamedQuery ( name="qPageTraitement.countAll", query="SELECT COUNT(x) FROM qPageTraitement x" )
} )
public class qPageTraitement extends qPageCarnet {
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
   // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link
    @Column
    @Enumerated
    @ElementCollection(targetClass = enumAuthorisation.class)
    private List<enumAuthorisation> segs;

    @OneToOne
    private qTraitement     qtraitement;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------


    @OneToMany(mappedBy = "pagesTraitement",targetEntity = qUniteTraitement.class)
    private List<qUniteTraitement> opTraitements;
    //----------------------------------------------------------------------


    public qPageTraitement(List<enumAuthorisation> segs, qTraitement qtraitement, List<qUniteTraitement> opTraitements) {
        this.segs = segs;
        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }



    public qPageTraitement(Integer nbrLigne, List<enumAuthorisation> segs, qTraitement qtraitement, List<qUniteTraitement> opTraitements) {

        this.segs = segs;
        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }


    public qPageTraitement() {

    }

    public List<enumAuthorisation> getSegs() {
        return segs;
    }

    public void setSegs(List<enumAuthorisation> segs) {
        this.segs = segs;
    }

    public qTraitement getQtraitement() {
        return qtraitement;
    }

    public void setQtraitement(qTraitement qtraitement) {
        this.qtraitement = qtraitement;
    }



    public List<qUniteTraitement> getOpTraitements() {
        return opTraitements;
    }

    public void setOpTraitements(List<qUniteTraitement> opTraitements) {
        this.opTraitements = opTraitements;
    }

    public qPageTraitement(String numeroPage,  Long numeroOrdrePage,enumEtatPage etat ,qCarnet carnet, List<enumAuthorisation> segs, qTraitement qtraitement, List<qUniteTraitement> opTraitements) {
        super(numeroPage, carnet,numeroOrdrePage,etat);
        this.segs = segs;
        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }
}
