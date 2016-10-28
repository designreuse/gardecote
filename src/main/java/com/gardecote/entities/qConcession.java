/*
 * Created on 8 oct. 2016 ( Time 01:00:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotasConcessions"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qConcession", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qConcession.countAll", query="SELECT COUNT(x) FROM qConcession x" )
} )
public class qConcession implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ref_concession", nullable=false)
    private String     refConcession ;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @OneToOne

    private qConsignataire    concessionaire;

    @Column(name="NumLicence", nullable=false, length=50)
    private String     numlicence   ;

    @Column(name="date_licence", nullable=false, length=10)
    private Date     dateLicence  ;

    @Column(name="date_debut", nullable=false, length=10)
    private Date dateDebut    ;

    @Column(name="date_fin", nullable=false, length=10)
    private Date     dateFin      ;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    @OneToMany(mappedBy="qconcession", targetEntity=qLicence.class)
    private List<qLicence>     qLicenceBatLastList;



    @OneToMany(mappedBy = "concession",targetEntity =qCategRessource.class )
    private List<qCategRessource>     categoriesRessources ;

    //private List<qCategRessource>     categoriesRessources ;
   //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------

    public qConcession()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setRefConcession( String refConcession )
    {
        this.refConcession = refConcession ;
    }
    public String getRefConcession()
    {
        return this.refConcession;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : SegmPeche ( varchar ) 


    //--- DATABASE MAPPING : type_concession ( varchar ) 
    public void setCategoriesRessources( List<qCategRessource> catRes )
    {
        this.categoriesRessources = catRes;
    }
    public List<qCategRessource> getCategoriesRessources()
    {
        return this.categoriesRessources;
    }

    //--- DATABASE MAPPING : Concessionaire ( varchar ) 
    public void setConcessionaire( qConsignataire concessionaire )
    {
        this.concessionaire = concessionaire;
    }
    public qConsignataire getConcessionaire()
    {
        return this.concessionaire;
    }

    //--- DATABASE MAPPING : NumLicence ( varchar )
    public void setNumlicence( String numlicence )
    {
        this.numlicence = numlicence;
    }
    public String getNumlicence()
    {
        return this.numlicence;
    }

    //--- DATABASE MAPPING : date_licence ( date ) 
    public void setDateLicence( Date dateLicence )
    {
        this.dateLicence = dateLicence;
    }
    public Date getDateLicence()
    {
        return this.dateLicence;
    }

    //--- DATABASE MAPPING : date_debut ( date ) 
    public void setDateDebut( Date dateDebut )
    {
        this.dateDebut = dateDebut;
    }
    public Date getDateDebut()
    {
        return this.dateDebut;
    }

    //--- DATABASE MAPPING : date_fin ( date ) 
    public void setDateFin( Date dateFin )
    {
        this.dateFin = dateFin;
    }
    public Date getDateFin()
    {
        return this.dateFin;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------


    public List<qLicence> getqLicenceBatLastList() {
        return qLicenceBatLastList;
    }

    public void setqLicenceBatLastList(List<qLicence> qLicenceBatLastList) {
        this.qLicenceBatLastList = qLicenceBatLastList;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(refConcession);
        sb.append("]:"); 

        sb.append("|");
        sb.append(categoriesRessources );
        sb.append("|");
        sb.append(concessionaire);

        sb.append("|");
        sb.append(numlicence);
        sb.append("|");
        sb.append(dateLicence);
        sb.append("|");
        sb.append(dateDebut);
        sb.append("|");
        sb.append(dateFin);
        return sb.toString(); 
    }

    public qConcession(String refConcession, qConsignataire concessionaire, String numlicence, Date dateLicence, Date dateDebut, Date dateFin, List<qCategRessource> categoriesRessources, List<qLicence> qLicenceBatLastList) {
        this.refConcession = refConcession;
        this.concessionaire = concessionaire;
        this.numlicence = numlicence;
        this.dateLicence = dateLicence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categoriesRessources = categoriesRessources;
        this.qLicenceBatLastList = qLicenceBatLastList;
    }
}