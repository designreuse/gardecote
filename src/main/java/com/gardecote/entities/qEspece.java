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
 * Persistent class for entity stored in table "quotaEspeces"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qEspece", schema="dbo", catalog="GCM5" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qEspece.countAll", query="SELECT COUNT(x) FROM qEspece x" )
} )
public class qEspece implements Serializable
{
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
   //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Code_Esp", nullable=false)
    private String   codeEsp      ;

  @OneToMany(cascade = CascadeType.ALL)
  private List<qEspeceTypee> qespecetypee;
  // @OneToMany
  //  qOpTraitement qopTraitement;
   //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="Nom_Ar", nullable=false, length=50)
    private String     nomAr        ;

    @Column(name="Nom_Fr", nullable=false, length=50)
    private String     nomFr        ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qEspece()
    {
		super();
    }

    public qEspece(String codeEsp, String nomAr, String nomFr, Integer numOrdre) {
        this.codeEsp = codeEsp;
        this.nomAr = nomAr;
        this.nomFr = nomFr;

    }

    public List<qEspeceTypee> getQespecetypee() {
        return qespecetypee;
    }

    public void setQespecetypee(List<qEspeceTypee> qespecetypee) {
        this.qespecetypee = qespecetypee;
    }



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodeEsp( String codeEsp )
    {
        this.codeEsp = codeEsp ;
    }
    public String getCodeEsp()
    {
        return this.codeEsp;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : Nom_Ar ( varchar ) 
    public void setNomAr( String nomAr )
    {
        this.nomAr = nomAr;
    }
    public String getNomAr()
    {
        return this.nomAr;
    }

    //--- DATABASE MAPPING : Nom_Fr ( varchar ) 
    public void setNomFr( String nomFr )
    {
        this.nomFr = nomFr;
    }
    public String getNomFr()
    {
        return this.nomFr;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(codeEsp);
        sb.append("]:"); 
        sb.append(nomAr);
        sb.append("|");
        sb.append(nomFr);
        return sb.toString(); 
    } 

}