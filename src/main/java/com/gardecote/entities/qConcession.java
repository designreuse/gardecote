/*
 * Created on 8 oct. 2016 ( Time 01:00:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="qConcession", schema="dbo", catalog="GCM11" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qConcession.countAll", query="SELECT COUNT(x) FROM qConcession x" )
} )
public class qConcession implements Serializable, Comparable<qConcession>
{
    private static final long serialVersionUID = 2L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ref_concession", nullable=false)
    private String     refConcession ;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
   // @Column(name="idconcession", nullable=false)
    // ajouter cela apres l import
    @ManyToOne(cascade = CascadeType.PERSIST)
    private qConsignataire    qconsignataire;

    @OneToMany(targetEntity = qNavireLegale.class,mappedBy = "concession" )
    private  List<qNavireLegale> navires;

    @OneToMany(mappedBy = "qconcession",targetEntity = qDoc.class)
    private List<qDoc> qdocs;

   @DateTimeFormat(pattern = "yyyy-MM-dd")
   @Column(name="dateConcession")
   private Date     dateConcession  ;

    @Column(name="quotaConcession")
    private Integer     quotaEnTonne  ;
    @Column(name="dureeconcession" )
    private Integer dureeConcession;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_debut")
    private Date dateDebut    ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_fin")
    private Date     dateFin      ;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    @OneToMany(mappedBy="qconcession", targetEntity=qLicenceNational.class)
    private List<qLicenceNational>     qLicenceBatLastList;

    @ManyToMany(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY)
    @JoinTable(name = "qAssocConessionCategRessources")
    private List<qCategRessource>     categoriesRessources ;

    //private List<qCategRessource>     categoriesRessources ;
   //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------

    public List<qNavireLegale> getNavires() {
        return navires;
    }

    public void setNavires(List<qNavireLegale> navires) {
        this.navires = navires;
    }

    public List<qDoc> getQdocs() {
        return qdocs;
    }

    public void setQdocs(List<qDoc> qdocs) {
        this.qdocs = qdocs;
    }

    public Date getDateConcession() {
        return dateConcession;
    }

    public void setDateConcession(Date dateConcession) {
        this.dateConcession = dateConcession;
    }

    public Integer getDureeConcession() {
        return dureeConcession;
    }

    public void setDureeConcession(Integer dureeConcession) {
        this.dureeConcession = dureeConcession;
    }


    public qConcession()
    {
		super();
    }


    public Integer getQuotaEnTonne() {
        return quotaEnTonne;
    }

    public void setQuotaEnTonne(Integer quotaEnTonne) {
        this.quotaEnTonne = quotaEnTonne;
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

    public qConsignataire getQconsignataire() {
        return qconsignataire;
    }

    public void setQconsignataire(qConsignataire qconsignataire) {
        this.qconsignataire = qconsignataire;
    }

    //--- DATABASE MAPPING : date_licence ( date )
    public void setDateLicence( Date dateLicence )
    {
        this.dateConcession = dateLicence;
    }
    public Date getConcession()
    {
        return this.dateConcession;
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
    public List<qLicenceNational> getqLicenceBatLastList() {
        return qLicenceBatLastList;
    }
    public void setqLicenceBatLastList(List<qLicenceNational> qLicenceBatLastList) {
        this.qLicenceBatLastList = qLicenceBatLastList;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------

    public qConcession(String refConcession, qConsignataire concessionaire, Date dateLicence, Date dateDebut, Date dateFin, List<qCategRessource> categoriesRessources, List<qLicenceNational> qLicenceBatLastList) {
        this.refConcession = refConcession;
        this.qconsignataire = concessionaire;
        this.dateConcession = dateLicence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.categoriesRessources=categoriesRessources;
        this.qLicenceBatLastList = qLicenceBatLastList;
    }
    @Override
    public int compareTo(qConcession o) {
        //String qespeceIDo=o.getNumOrdre().toString()+o.getQespeceId().toString()+o.getEnumesptype().toString();
        // String qespeceID=this.getNumOrdre().toString()+this.qespeceId.toString()+this.getEnumesptype().toString();
        String qespeceIDo=o.getRefConcession();
        String qespeceID=this.getRefConcession();
        return qespeceID.compareTo(qespeceIDo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qConcession)) return false;

        qConcession that = (qConcession) o;

        return getRefConcession().equals(that.getRefConcession());

    }

    @Override
    public int hashCode() {
        return getRefConcession().hashCode();
    }
}