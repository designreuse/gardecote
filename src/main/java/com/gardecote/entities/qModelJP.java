/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaEspecesTypeConcession"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qModelJP", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qModelJP.countAll", query="SELECT COUNT(x) FROM qModelJP x" )
} )

public class qModelJP implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @NotNull
    @Column(name="prefixModel", nullable=false, length=2)
    private enumPrefix prefixModel;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
	// "codeesp" (column "CodeEsp") is not defined by itself because used as FK in a link 
	// "typeconcession" (column "typeConcession") is not defined by itself because used as FK in a link 
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToMany(cascade = CascadeType.MERGE,targetEntity =qEspeceTypee.class,fetch = FetchType.EAGER)
    @JoinTable(name = "qAssocModelEspeceTypee")
    private List<qEspeceTypee> especestypees;




    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qModelJP()
    {
		super();
    }

    public qModelJP(enumPrefix prefixModel, List<qEspeceTypee> especestypees) {
        this.prefixModel = prefixModel;
        this.especestypees = especestypees;
    }

    public enumPrefix getPrefixModel() {
        return prefixModel;
    }

    public void setPrefixModel(enumPrefix prefixModel) {
        this.prefixModel = prefixModel;
    }

    public List<qEspeceTypee> getEspecestypees() {
        return especestypees;
    }

    public void setEspecestypees(List<qEspeceTypee> especestypees) {
        this.especestypees = especestypees;
    }
}