/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;
/**
 * Persistent class for entity stored in table "quotaMarrees"
 *
 * @author Telosys Tools Generator
 *
 */
@Entity
@DynamicUpdate
@DiscriminatorValue("MARREE")
// @Table(name="qMarree", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qMarree.countAll", query="SELECT COUNT(x) FROM qMarree x" )
} )

public class qMarree extends qDoc implements Serializable
{
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------
    private enumJP typeJP;
    @ManyToMany(targetEntity = qEnginPecheMar.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocMarreeEnginsBIS")
    private List<qEnginPecheMar> qEngins;
    @OneToMany(targetEntity=qPageMarree.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocMareesPagesBIS")
    private List<qPageMarree> pages;
    @OneToOne(targetEntity = qMarreeAnnexe.class,cascade = {CascadeType.MERGE})
    private qMarreeAnnexe marreeAnnexe;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
   public qMarree() {
    }
//----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public qMarreeAnnexe getMarreeAnnexe() {
        return marreeAnnexe;
    }

    public void setMarreeAnnexe(qMarreeAnnexe marreeAnnexe) {
        this.marreeAnnexe = marreeAnnexe;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public qMarree(enumTypeDoc enumtypedoc, Date depart, Date retour, qSeq qseq, qNavire qnavire,qUsine qusine,qConcession qconcess, enumJP typeJP, List<qEnginPecheMar> qEngins, List<qPageMarree> pages) {
        super(enumtypedoc, depart, retour,qseq, qnavire,qusine,qconcess);

        this.typeJP = typeJP;
        this.qEngins = qEngins;
        this.pages = pages;
    }

    //--- DATABASE MAPPING : Depart ( date )
   public enumJP getTypeJP() {
        return typeJP;
    }

    public void setTypeJP(enumJP typeJP) {
        this.typeJP = typeJP;
    }

    public List<qEnginPecheMar> getqEngins() {
        return qEngins;
    }

    public void setqEngins(List<qEnginPecheMar> qEngins) {
        this.qEngins = qEngins;
    }

    public List<qPageMarree> getPages() {
        return pages;
    }

    public void setPages(List<qPageMarree> pages) {
        this.pages = pages;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 

        sb.append("]:"); 

        sb.append("|");

        sb.append("|");


        sb.append("|");

        return sb.toString(); 
    } 

}