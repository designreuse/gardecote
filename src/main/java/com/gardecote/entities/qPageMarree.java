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
@NamedQueries ({
        @NamedQuery ( name="qPageMarree.countAll", query="SELECT COUNT(x) FROM qPageMarree x" )
})
public class qPageMarree extends qPageCarnet implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    // "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link
    @OneToOne(targetEntity = qMarree.class,cascade = CascadeType.ALL)
    private qMarree     qmarree;
    @OneToMany(targetEntity=qEspeceDynamic.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocPagesMareesEspDyn")
    private List<qEspeceDynamic> especesDyn;
    @Column(name="totalCaptures", nullable=false, length=10)
    private float totalCapturs;
    @Column(name="totalCongs", nullable=true, length=10)
    private float totalCong;
    @Column(name="totalNbrCaisses", nullable=true, length=10)
    private float nbrCaisse;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy = "pageMarree",targetEntity = qJourMere.class,cascade = CascadeType.ALL)
    private List<qJourMere> listJours;

   //----------------------------------------------------------------------
    public qPageMarree() {
         }
    public qPageMarree(qMarree qmarree, List<qJourMere> listJours) {
        this.qmarree = qmarree;
        this.listJours = listJours;
    }

    public List<qEspeceDynamic> getEspecesDyn() {
        return especesDyn;
    }

    public void setEspecesDyn(List<qEspeceDynamic> especesDyn) {
        this.especesDyn = especesDyn;
    }

    public qPageMarree(String numeroPage, Long numeroOrdrePage, enumEtatPage etat, qCarnet carnet, qMarree qmarree, List<qJourMere> listJours, List<qEspeceDynamic> especesDyn,float totalCapturs,float totalCong,float nbrCaisse) {
        super(numeroPage,  carnet,numeroOrdrePage,etat);
        this.qmarree = qmarree;
        this.listJours = listJours;
        this.especesDyn=especesDyn;
        this.totalCapturs=totalCapturs;
        this.totalCong=totalCong;
        this.totalCapturs=totalCapturs;
        this.totalCong=totalCong;
        this.nbrCaisse=nbrCaisse;
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

    public float getTotalCapturs() {
        return totalCapturs;
    }

    public void setTotalCapturs(float totalCapturs) {
        this.totalCapturs = totalCapturs;
    }

    public float getTotalCong() {
        return totalCong;
    }

    public void setTotalCong(float totalCong) {
        this.totalCong = totalCong;
    }

    public float getNbrCaisse() {
        return nbrCaisse;
    }

    public void setNbrCaisse(float nbrCaisse) {
        this.nbrCaisse = nbrCaisse;
    }
}
