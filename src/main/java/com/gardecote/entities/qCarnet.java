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
import org.springframework.format.annotation.NumberFormat;
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


    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
  //  @Enumerated(EnumType.STRING)
    @Column(name="prefixNum", nullable=false, length=2)
    private enumPrefix     prefixNumerotation    ;
    @Id

    @Column(name="debutPage1")
    private Long       numeroDebutPage    ;


    private enumTypeDoc  typeDoc;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    



    @Column(name="NbrPages", nullable=false)
    private Integer    nbrPages      ;
    @Column(name="NbrLignes", nullable=false)
    private Integer    nbrLigneParPage      ;
    @OneToOne
    @JoinColumn(name="qnavire")
    private qRegistreNavire      qnavire  ;
    @OneToOne
    @JoinColumn(name="qusine")
    private qUsine     qusine  ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="qcarnet",cascade = CascadeType.ALL, targetEntity=qPageCarnet.class)
    private List<qPageCarnet> pages ;


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


    public qCarnet(enumTypeDoc enumtypedoc,enumPrefix enumprefix, Long numeroDebutPage, Integer nbrPages) {

        this.typeDoc=enumtypedoc;
        this.numeroDebutPage = numeroDebutPage;
        this.prefixNumerotation = enumprefix;
        this.qnavire = null;
        this.qusine = null;
        this.nbrPages = nbrPages;
        this.nbrLigneParPage=0;

      //    this.modeCarnet = modeCarnet;
         //    this.refLicencement = refLicencement;this.pages = pages;
        // ajouter des pages du carnet encours




    }

    public void distribuer(qRegistreNavire qnavire,qUsine qusine){

        this.qnavire =qnavire;
        this.qusine = qusine;
    }

    public qUsine getQusine() {
        return qusine;
    }

    public void setQusine(qUsine qusine) {
        this.qusine = qusine;
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


    public qRegistreNavire getQnavire() {
        return qnavire;
    }

    public void setQnavire(qRegistreNavire qnavire) {
        this.qnavire = qnavire;
    }

    public List<qPageCarnet> getPages() {
        return pages;
    }

    public void setPages(List<qPageCarnet> pages) {
        this.pages = pages;
    }
}