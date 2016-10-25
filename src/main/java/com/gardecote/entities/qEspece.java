/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaEspeces"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qEspece", schema="dbo", catalog="DSPCM_DB" )
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Code_Esp", nullable=false)
    private Integer    codeEsp      ;



    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "modelesp_segment", referencedColumnName = "segPeche"),
            @JoinColumn(name = "modelesp_categorie", referencedColumnName = "categorie")

    })

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="Nom_Ar", nullable=false, length=50)
    private String     nomAr        ;

    @Column(name="Nom_Fr", nullable=false, length=50)
    private String     nomFr        ;
    @Column(name="Num_Ordre", nullable=false, length=50)
    private Integer     numOrdre        ;


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

    public qEspece(String nomAr, String nomFr,Integer numOrdre,qModelJP qm) {
        this.nomAr = nomAr;
        this.nomFr = nomFr;
        this.numOrdre=numOrdre;

    }

    public Integer getNumOrdre() {
        return numOrdre;
    }



    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodeEsp( Integer codeEsp )
    {
        this.codeEsp = codeEsp ;
    }
    public Integer getCodeEsp()
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