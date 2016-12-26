package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
//@Table(name="qPageMarree", schema="dbo", catalog="DSPCM_DB" )
@DiscriminatorValue("PANNEXE")
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qPageAnnexe.countAll", query="SELECT COUNT(x) FROM qPageAnnexe x" )
} )
public class qPageAnnexe extends qPageCarnet implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link
   @OneToOne(cascade = CascadeType.ALL)
    private qMarreeAnnexe     qmarreeAnexe;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy = "pageMarree",targetEntity = qJourMereAnnexe.class,cascade = CascadeType.ALL)
    private List<qJourMereAnnexe> listJours;

   //----------------------------------------------------------------------
    public qPageAnnexe() {
         }
    public qPageAnnexe(qMarreeAnnexe qmarree, List<qJourMereAnnexe> listJours) {
        this.qmarreeAnexe = qmarree;
        this.listJours = listJours;
    }


    public qPageAnnexe(String numeroPage, Long numeroOrdrePage, enumEtatPage etat, qCarnet carnet, qMarreeAnnexe qmarree, List<qJourMereAnnexe> listJours) {
        super(numeroPage,  carnet,numeroOrdrePage,etat);
        this.qmarreeAnexe = qmarree;
        this.listJours = listJours;
    }

    public qMarreeAnnexe getQmarreeAnexe() {
        return qmarreeAnexe;
    }

    public void setQmarreeAnexe(qMarreeAnnexe qmarreeAnexe) {
        this.qmarreeAnexe = qmarreeAnexe;
    }

    public List<qJourMereAnnexe> getListJours() {
        return listJours;
    }

    public void setListJours(List<qJourMereAnnexe> listJours) {
        this.listJours = listJours;
    }
}
