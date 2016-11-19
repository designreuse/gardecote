/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.List;
//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaPageCarnet"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
//@Table(name="qPageDebarquement", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@DiscriminatorValue("PDEBARQUEMENT")
@NamedQueries ( {
  @NamedQuery ( name="qPageDebarquement.countAll", query="SELECT COUNT(x) FROM qPageDebarquement x" )
} )
public class qPageDebarquement extends qPageCarnet implements Serializable
{
    private static final long serialVersionUID = 1L;


    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
     // ENTITY DATA FIELDS
    //----------------------------------------------------------------------


	// "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link 


    @OneToOne(cascade = CascadeType.ALL)
    private qDebarquement qdebarquement;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------


    @OneToMany(mappedBy = "pagesDeb",targetEntity = qJourDeb.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)

   // @NotFound(action=NotFoundAction.IGNORE)
    private List<qJourDeb>  listJours;

    public qPageDebarquement(String numeroPage,Long numeroOrdrePage, enumEtatPage etat, qCarnet carnet, qDebarquement qdebarquement, List<qJourDeb> listJours) {
        super(numeroPage,  carnet,numeroOrdrePage,etat);

        this.qdebarquement = qdebarquement;
        this.listJours = listJours;
    }

    public qPageDebarquement() {

    }


    public qDebarquement getQdebarquement() {
        return qdebarquement;
    }

    public void setQdebarquement(qDebarquement qdebarquement) {
        this.qdebarquement = qdebarquement;
    }


    public List<qJourDeb> getListJours() {
        return listJours;
    }

    public void setListJours(List<qJourDeb> listJours) {
        this.listJours = listJours;
    }
}