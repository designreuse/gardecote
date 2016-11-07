package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
@Entity
//@Table(name="qPageMarree", schema="dbo", catalog="DSPCM_DB" )
@DiscriminatorValue("PMARREE")
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qPageMarree.countAll", query="SELECT COUNT(x) FROM qPageMarree x" )
} )
public class qPageMarree extends qPageCarnet implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link


    @OneToOne
    private qConcession qconcession;

    @OneToOne
    private qMarree     qmarree;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------


    @OneToMany(mappedBy = "pagesMarree",targetEntity = qJourMere.class)
    private List<qJourMere> listJours;
   //----------------------------------------------------------------------


    public qPageMarree() {
         }
    public qPageMarree(qMarree qmarree, List<qJourMere> listJours) {
        this.qmarree = qmarree;
        this.listJours = listJours;
    }
    public qPageMarree(String numeroPage,Long numeroOrdrePage,  enumEtatPage etat,qCarnet carnet, qMarree qmarree, List<qJourMere> listJours) {
        super(numeroPage,  carnet,numeroOrdrePage,etat);
        this.qmarree = qmarree;
        this.listJours = listJours;
    }

    public qMarree getQmarree() {
        return qmarree;
    }

    public void setQmarree(qMarree qmarree) {
        this.qmarree = qmarree;
    }



    public List<qJourMere> getListJours() {
        return listJours;
    }

    public void setListJours(List<qJourMere> listJours) {
        this.listJours = listJours;
    }
}
