package com.gardecote.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
public class qPageTraitement extends qPageCarnet {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------

   private  Integer  nbrLigne=10;
    // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link


    public  Integer getNbrLigne() {
        return nbrLigne;
    }

    private List<enumAuthorisation> segs;

    @OneToOne
    private qTraitement     qtraitement;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="IdCarnet", referencedColumnName="IdCarnet")
    private qCarnet carnet;

    @OneToMany(mappedBy = "pages",targetEntity = qJourMere.class)
    private List<qUniteTraitement> opTraitements;
    //----------------------------------------------------------------------


    public qPageTraitement(Integer nbrLigne, List<enumAuthorisation> segs, qTraitement qtraitement, List<qUniteTraitement> opTraitements) {
        this.nbrLigne = nbrLigne;
        this.segs = segs;
        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }

    public qPageTraitement(String numeroPage, Integer nbrLigne, Integer nbrLigne1, List<enumAuthorisation> segs, qTraitement qtraitement, List<qUniteTraitement> opTraitements) {
        super(numeroPage, nbrLigne);
        nbrLigne = nbrLigne1;
        this.segs = segs;
        this.qtraitement = qtraitement;
        this.opTraitements = opTraitements;
    }

    public void setNbrLigne(Integer nbrLigne) {
        this.nbrLigne = nbrLigne;
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

    @Override
    public qCarnet getCarnet() {
        return carnet;
    }

    @Override
    public void setCarnet(qCarnet carnet) {
        this.carnet = carnet;
    }

    public List<qUniteTraitement> getOpTraitements() {
        return opTraitements;
    }

    public void setOpTraitements(List<qUniteTraitement> opTraitements) {
        this.opTraitements = opTraitements;
    }
}
