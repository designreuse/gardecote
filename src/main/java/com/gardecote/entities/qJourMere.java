/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaJours"
 *
 * @author Telosys Tools Generator
 *
 */
import java.util.Date;
@Entity
@Table(name="qJourMere4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qJour.countAll", query="SELECT COUNT(x) FROM qJourMere x" )
} )
@IdClass(qJourPK.class)
public class qJourMere implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id

    @Column(name="numimmJour", nullable=false)
    private String       numImm       ;

    @Id
    @Column(name="dateJour", nullable=false, length=10)
    private Date     dateJour     ;

    @OneToOne
    private qNavire navire;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

   //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="jourMere", targetEntity=qCapture.class)
    private List<qCapture> capturesDuMarree;
    @Column(name="totalCapturs", nullable=false, length=10)
    private Integer totalCapturs;
    @Column(name="totalCong", nullable=false, length=10)
    private Integer totalCong;
    @Column(name="nbrCaisse", nullable=false, length=10)
    private Integer nbrCaisse;

    @ManyToOne
    private qPageMarree pageMarree;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qJourMere()
    {
		super();
    }

    public Integer getTotalCapturs() {
        return totalCapturs;
    }

    public void setTotalCapturs(Integer totalCapturs) {
        this.totalCapturs = totalCapturs;
    }

    public Integer getTotalCong() {
        return totalCong;
    }

    public void setTotalCong(Integer totalCong) {
        this.totalCong = totalCong;
    }

    public Integer getNbrCaisse() {
        return nbrCaisse;
    }

    public void setNbrCaisse(Integer nbrCaisse) {
        this.nbrCaisse = nbrCaisse;
    }

    public qJourMere(Date datejourMere,qNavire navire, List<qCapture> capturesDuMarree, Integer totalCapturs, Integer totalCong, Integer nbrCaisse,qPageMarree pageMarree) {
        this.dateJour = datejourMere;
        this.capturesDuMarree = capturesDuMarree;
        this.totalCapturs = totalCapturs;
        this.totalCong = totalCong;
        this.nbrCaisse = nbrCaisse;
        this.numImm=navire.getNumimm();
        this.setPageMarree(pageMarree);

    }

    public String getNumImm() {
        return numImm;
    }
    public qJourPK getJourMerPK(){
    qJourPK qjourpk=new qJourPK(this.getDatejourMere(),this.getNumImm().toString());
      return qjourpk;
    }
    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public qNavire getNavire() {
        return navire;
    }

    public void setNavire(qNavire navire) {
        this.navire = navire;
    }

    public Date getDatejourMere() {
        return dateJour;
    }

    public void setDatejourMere(Date datejourMere) {
        this.dateJour = datejourMere;
    }

    public List<qCapture> getCapturesDuMarree() {
        return capturesDuMarree;
    }

    public void setCapturesDuMarree(List<qCapture> capturesDuMarree) {
        this.capturesDuMarree = capturesDuMarree;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public qPageMarree getPageMarree() {
        return pageMarree;
    }

    public void setPageMarree(qPageMarree pageMarree) {
        this.pageMarree = pageMarree;
    }
}