/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaJourCaptJours"
 *
 * @author Telosys Tools Generator
 *
 */
import java.util.Date;
@Entity
@Table(name="qCapture20", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qCaptue.countAll", query="SELECT COUNT(x) FROM qCapture x" )
} )
@IdClass(qCapturePK.class)
public class qCapture implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
     private    Date        datedepart;
    @Id
     private    String      nummimm;
    @Id
     private    Date        dateJour;
    @Id
     private    String      idespece;
    @Id
     private    enumEspType esptype;

    @OneToOne(cascade = CascadeType.ALL)

    private qDoc qdoc;
    @OneToOne
    private qEspeceTypee especeTypee;

    private Integer quantite;

    @ManyToOne
    private qJourMere jourMere;

    @ManyToOne
    private qJourDeb jourDeb;

    public qEspeceTypee getEspeceTypee() {
        return especeTypee;
    }

    public void setEspeceTypee(qEspeceTypee especeTypee) {
        this.especeTypee = especeTypee;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

   public String getNummimm() {
        return nummimm;
    }

    public void setNummimm(String nummimm) {
        this.nummimm = nummimm;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public String getIdespece() {
        return idespece;
    }

    public void setIdespece(String idespece) {
        this.idespece = idespece;
    }

    public enumEspType getEsptype() {
        return esptype;
    }

    public void setEsptype(enumEspType esptype) {
        this.esptype = esptype;
    }

    public qDoc getQdoc() {
        return qdoc;
    }

    public void setQdoc(qDoc qdoc) {
        this.qdoc = qdoc;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public qJourMere getJourMere() {
        return jourMere;
    }

    public void setJourMere(qJourMere jourMere) {
        this.jourMere = jourMere;
    }

    public qJourDeb getJourDeb() {
        return jourDeb;
    }

    public void setJourDeb(qJourDeb jourDeb) {
        this.jourDeb = jourDeb;
    }

    public qCapture() {
    }

     public qCapturePK  getCapturePK() {
         qCapturePK qcappk=new qCapturePK(datedepart,nummimm,dateJour,idespece,esptype);
         return qcappk;
      }

    public qCapture(qDoc qdoc, qEspeceTypee especeTypee, Integer quantite, qJourMere jourMere, qJourDeb jourDeb) {
        this.datedepart = qdoc.getDepart();
        this.nummimm = qdoc.getNumImm();
        if(qdoc.getEnumtypedoc().equals(enumTypeDoc.Fiche_Debarquement))this.dateJour = jourDeb.getDatejourDeb();
        if(qdoc.getEnumtypedoc().equals(enumTypeDoc.Journal_Peche)) this.dateJour = jourMere.getDatejourMere();
        this.idespece = especeTypee.getQespeceId();
        this.esptype = especeTypee.getEnumesptype();
        this.qdoc = qdoc;
        this.especeTypee = especeTypee;
        this.quantite = quantite;
        this.jourMere = jourMere;
        this.jourDeb = jourDeb;
    }
}