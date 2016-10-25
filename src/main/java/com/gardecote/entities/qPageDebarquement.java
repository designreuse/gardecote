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
 * Persistent class for entity stored in table "quotaPageCarnet"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qPageDebarquement", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qPageDebarquement.countAll", query="SELECT COUNT(x) FROM qPageDebarquement x" )
} )
public class qPageDebarquement extends qPageCarnet implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer  nbrLigne=10;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
     // ENTITY DATA FIELDS
    //----------------------------------------------------------------------


	// "idcarnet" (column "IdCarnet") is not defined by itself because used as FK in a link 



    @OneToOne
    private qDebarquement    qdebarquement;
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="IdCarnet", referencedColumnName="IdCarnet")
    private qCarnet carnet;

    @OneToMany(mappedBy = "pages",targetEntity = qJourDeb.class)
    private List<qJourDeb>  listJours;




    public qDebarquement getQdebarquement() {
        return qdebarquement;
    }

    public void setQdebarquement(qDebarquement qdebarquement) {
        this.qdebarquement = qdebarquement;
    }

    @Override
    public qCarnet getCarnet() {
        return carnet;
    }

    @Override
    public void setCarnet(qCarnet carnet) {
        this.carnet = carnet;
    }

    public List<qJourDeb> getListJours() {
        return listJours;
    }

    public void setListJours(List<qJourDeb> listJours) {
        this.listJours = listJours;
    }
}