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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="qCarnet", schema="dbo", catalog="GCM11" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qCarnet.countAll", query="SELECT COUNT(x) FROM qCarnet x" )
} )
@IdClass(qCarnetPK.class)
public class qCarnet implements Serializable , Comparable<qCarnet>
{
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
  // @Enumerated(EnumType.STRING)
    @Column(name="prefixNum", nullable=false, length=5)
    private String   prefixNumerotation    ;

    @Id
    @Column(name="debutPage1")
    private Long       numeroDebutPage    ;
    @Id
    private enumTypeDoc  typeDoc;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="NbrPages", nullable=false)
    private Integer    nbrPages      ;
    @Column(name="NbrLignes", nullable=false)
    private Integer    nbrLigneParPage      ;
    @OneToOne(targetEntity = qNavireLegale.class)
    @JoinColumn(name="qnavire")
    private qNavireLegale      qnavire  ;
    @OneToOne(targetEntity = qPrefix.class)
    //@JoinColumn(name="qprefix")
    private qPrefix      qprefix  ;
    @OneToOne(targetEntity = qUsine.class)
    @JoinColumn(name="qusine")
    private qUsine     qusine  ;
    @OneToOne
    private qConcession qconcession;
   //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy ="qcarnet",cascade = CascadeType.ALL,targetEntity=qPageCarnet.class)
  //  @JoinColumns({
  //          @JoinColumn(name = "debutPage1fk", referencedColumnName = "debutPage1",insertable = false,updatable = false),
  //          @JoinColumn(name = "prefixNumfk", referencedColumnName = "prefixNum",insertable = false,updatable = false)
  //  })

    private List<qPageCarnet> pages ;
   //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------

    public qPrefix getQprefix() {
        return this.qprefix;
    }

    public void setQprefix(qPrefix qprefix) {
        this.qprefix = qprefix;
    }

    public qCarnet()
    {
		super();
    }
   public qCarnetPK getCarnetPK() {
       qCarnetPK qCarnPK=new qCarnetPK(prefixNumerotation,numeroDebutPage,typeDoc);
       return qCarnPK;
   }
    public Integer getNbrLigneParPage() {
        return nbrLigneParPage;
    }

    public void setNbrLigneParPage(Integer nbrLigneParPage) {
        this.nbrLigneParPage = nbrLigneParPage;
    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
    }

    public qCarnet(qPrefix prefix, Long numeroDebutPage, Integer nbrPages,qNavireLegale navire,qUsine usine) {
        this.qprefix=prefix;
        this.typeDoc=prefix.getTypeDoc();
        this.numeroDebutPage = numeroDebutPage;
        this.prefixNumerotation = prefix.getPrefix();
        if(this.typeDoc==enumTypeDoc.Fiche_Debarquement || this.typeDoc==enumTypeDoc.Journal_Peche || this.typeDoc==enumTypeDoc.Journal_Annexe )this.qnavire = navire;
        if(this.typeDoc==enumTypeDoc.Fiche_Traitement ) this.qusine = usine;
        this.qconcession=null;
        this.nbrPages = nbrPages;
        this.nbrLigneParPage=prefix.getNbrLigneCarnet();

      //    this.modeCarnet = modeCarnet;
         //    this.refLicencement = refLicencement;this.pages = pages;
        // ajouter des pages du carnet encours
    }

    public void distribuer(qNavireLegale qnavire,qUsine qusine){

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

    public String getPrefixNumerotation() {
        return prefixNumerotation;
    }

    public void setPrefixNumerotation(String prefixNumerotation) {
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

    public qNavireLegale getQnavire() {
        return qnavire;
    }

    public void setQnavire(qNavireLegale qnavire) {
        this.qnavire = qnavire;
    }

    public List<qPageCarnet> getPages() {
        return pages;
    }

    public void setPages(List<qPageCarnet> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCarnet)) return false;

        qCarnet qCarnet = (qCarnet) o;

        if (!getPrefixNumerotation().equals(qCarnet.getPrefixNumerotation())) return false;
        if (!getNumeroDebutPage().equals(qCarnet.getNumeroDebutPage())) return false;
        return getTypeDoc() == qCarnet.getTypeDoc();

    }

    @Override
    public int hashCode() {
        int result = getPrefixNumerotation().hashCode();
        result = 31 * result + getNumeroDebutPage().hashCode();
        result = 31 * result + getTypeDoc().hashCode();
        return result;
    }
    @Override
    public int compareTo(qCarnet o) {
        //String qespeceIDo=o.getNumOrdre().toString()+o.getQespeceId().toString()+o.getEnumesptype().toString();
        // String qespeceID=this.getNumOrdre().toString()+this.qespeceId.toString()+this.getEnumesptype().toString();
        String prefo=o.getPrefixNumerotation();
        String pref=this.getPrefixNumerotation();

        enumTypeDoc typeDocO=o.getTypeDoc();
        enumTypeDoc typeDoc=this.getTypeDoc();

        String numeroDebutPageO=o.getNumeroDebutPage().toString();
        String numeroDebutPage=this.getNumeroDebutPage().toString();

       return prefo.concat(typeDocO.toString()).concat(numeroDebutPageO.toString()).compareTo(pref.concat(typeDoc.toString()).concat(numeroDebutPage.toString()));
    }
}