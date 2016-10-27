/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "quotaCarnets"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qCarnet", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qCarnet.countAll", query="SELECT COUNT(x) FROM qCarnet x" )
} )
@IdClass(qCarnetPK.class)
public class qCarnet implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="PrefixNum", nullable=false, length=2)
    private enumPrefix     prefixNumerotation    ;
    @Id
    @Column(name="DebutPage", nullable=false)
    private Long       numeroDebutPage    ;


    private enumTypeDoc  typeDoc;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    



    @Column(name="NbrPages", nullable=false)
    private Integer    nbrPages      ;
    @Column(name="NbrLigne", nullable=false)
    private Integer    nbrLigneParPage      ;

    @Column(name="refLicencement", nullable=true)
    private qConcession      qconcession  ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(cascade = CascadeType.ALL, mappedBy="carnet", targetEntity=qPageCarnet.class)
    private List<qPageCarnet> pages       ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qCarnet()
    {
		super();
    }
   public qCarnetPK getCarnetPK() {
       qCarnetPK qCarnPK=new qCarnetPK(prefixNumerotation,numeroDebutPage);
       return qCarnPK;
   }
    public Integer getNbrLigneParPage() {
        return nbrLigneParPage;
    }

    public void setNbrLigneParPage(Integer nbrLigneParPage) {
        this.nbrLigneParPage = nbrLigneParPage;
    }


    public qCarnet(enumPrefix prefixNumerotation, Long numeroDebutPage, Integer nbrPages) {

        this.prefixNumerotation = prefixNumerotation;
        this.numeroDebutPage = numeroDebutPage;
        this.nbrPages = nbrPages;
    //    this.modeCarnet = modeCarnet;
    //    this.refLicencement = refLicencement;
        this.pages = pages;
        // ajouter des pages du carnet encours
        qPageCarnet qpcrn=new qPageCarnet();
        qpcrn.setCarnet(this);

        List<qPageCarnet> lstPgs=new ArrayList<qPageCarnet>();
        for(int i=0;i<this.nbrPages;i++) {
            qPageCarnet  qp= new qPageCarnet();

            qp.setCarnet(this);

            qp.setNumeroPage(this.prefixNumerotation.toString()+Long.toString(this.numeroDebutPage+i));

            lstPgs.add(qp);
        }
     this.pages=lstPgs;
    }

    public void distribuer(qConcession refConcession){

    this.qconcession = refConcession;
    }



    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public enumPrefix getPrefixNumerotation() {
        return prefixNumerotation;
    }

    public void setPrefixNumerotation(enumPrefix prefixNumerotation) {
        this.prefixNumerotation = prefixNumerotation;
    }

    public Long getNumeroDebutPage() {
        return numeroDebutPage;
    }

    public void setNumeroDebutPage(Long numeroDebutPage) {
        this.numeroDebutPage = numeroDebutPage;
    }

    public Integer getNbrPages() {
        return nbrPages;
    }

    public void setNbrPages(Integer nbrPages) {
        this.nbrPages = nbrPages;
    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
    }

    public List<qPageCarnet> getPages() {
        return pages;
    }

    public void setPages(List<qPageCarnet> pages) {
        this.pages = pages;
    }
}