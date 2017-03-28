/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;
/**
 * Persistent class for entity stored in table "licences_bat_last"
 *
 * @author Telosys Tools Generator
 *
 */
@Entity
@Table(name="qlicence", schema="dbo", catalog="GCM11")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPELICENCE", discriminatorType=DiscriminatorType.STRING, length=20)
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qLic.countAll", query="SELECT COUNT(x) FROM  qLic x")
} )
public class qLic implements Serializable
{
    private static final long serialVersionUID = 1L;
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
  //  @NotEmpty(message = "le champs ne peut pas être null")
    @Column(name="numlic", length=255)
    private String     numlic       ;

    @Column(name = "created_at")
    public Date createdAt;

    @Column(name = "updated_at")
    public Date updatedAt;

    @Column(name="numimm", nullable=true)
     private String     numimm;

     private enumModePeche   modePeche ;

    @ManyToMany(targetEntity = qCategRessource.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocLicencesCategRessourcesBIS")
    @JsonBackReference
    //   @NotNull
    private List<qCategRessource>   qcatressources;

    @OneToMany(targetEntity=qEnginAuthorisee.class,cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<qEnginAuthorisee> enginsAuthorisees;

    @OneToMany(targetEntity=qNavireHistoriqueChangements.class,cascade = CascadeType.ALL)
    private List<qNavireHistoriqueChangements> historiqueChangements;
    @ManyToOne(targetEntity = qNavireLegale.class,cascade = CascadeType.ALL)
    @JsonBackReference

    private qNavireLegale qnavire;
    // remplissage de champs de navire a partir de batau selectionnee avec disabled ou enabled avec des nouveau saisi
    @Column(name="balise", length=255)
    //@NotEmpty
    private String     balise;
    @Column(name="nomnav", length=255)

    // @NotEmpty
    private String     nomnav;
    @Column(name="nomar", length=255)
    //@NotEmpty
    private String     nomar;

    @Column(name="longueur", length=255)
    //@NotEmpty
    private String     longg        ;

    @Column(name="larg", length=255)
    //@NotEmpty
    private String     larg         ;
    @Column(name="count", length=255)
    //@NotEmpty
    private String     count        ;

    @Column(name="nbrhomm", length=255)
    //@NotEmpty
    private String     nbrhomm      ;

    @Column(name="eff", length=255)
    //@NotEmpty
    private String     eff ;

    @Column(name="ancons", length=255)
    //@NotNull
    private Integer     anneeconstr       ;

    @Column(name="calpoids", length=255)
    //@NotEmpty
    private String     calpoids     ;
    @Column(name="gt")
    //@NotNull
    private float    gt           ;


    @Column(name="kw" )
    //@NotNull
    private float    kw           ;


    @Column(name="tjb")
    //@NotNull
    private float     tjb          ;

    @Column(name="imo")
    //@NotNull
    private Integer    imo          ;

    @Column(name="port", length=255)
    //@NotEmpty
    private String     port         ;

    @Column(name="puimot", length=255)
    //@NotEmpty
    private String     puimot       ;

    @Column(name="radio", length=255)
    //@NotEmpty
    private String     radio        ;
    // ca c'est la fin pour le format de nouvelle strategie
   // @Temporal(TemporalType.TIMESTAMP)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@NotNull

    @Column(name="debaut")
    private Date       dateDebutAuth      ;

 //   @Temporal(TemporalType.TIMESTAMP)
 //@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="finauto")
    private Date       dateFinAuth       ;
    // fin de remplissage de champs bateau

    // pour la compatibilté avec les anciens données

    @OneToOne
    //@Column(name="id_type_nav")
    private qTypeLic   qtypnav       ;

    @OneToOne
    //  @Column(name="id_zone")
    private qZone   zone       ;
    @OneToOne
    //  @Column(name="id_nation")
    private qNation    nation     ;

    @Column(name="typb", length=100)
    private enumTypeBat    typb         ;
    // Ca c'est pour le format de nouvelle strategie

    //----------------------------------------------------------------------vv
    // @Override


    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<qEnginAuthorisee> getEnginsAuthorisees() {
        return enginsAuthorisees;
    }

    public void setEnginsAuthorisees(List<qEnginAuthorisee> enginsAuthorisees) {
        this.enginsAuthorisees = enginsAuthorisees;
    }

    public qLic()
    {
        super();
    }

    public enumModePeche getModePeche() {
        return modePeche;
    }

    public void setModePeche(enumModePeche modePeche) {
        this.modePeche = modePeche;
    }

    public String getNomnav() {
        return nomnav;
    }

    public void setNomnav(String nomnav) {
        this.nomnav = nomnav;
    }

    public qNation getNation() {
        return nation;
    }

    public void setNation(qNation nation) {
        this.nation = nation;
    }

    public qLic(qTypeLic qtypnav, qZone zone, qNation qNation, List<qCategRessource> qcatressources, qNavireLegale qnavire, enumTypeBat typb, Date dateDebutAuth, Date dateFinAuth, Integer anneeconstr, String balise, String calpoids, String count, String eff, float gt, Integer imo, float kw, String larg, String longg, String nbrhomm, String nomar, String nomnav, String numlic, String port, String puimot, String radio, float tjb,List<qEnginAuthorisee> engins,enumModePeche   modePeche) {
        this.enginsAuthorisees=engins;
        this.qtypnav = qtypnav;
        this.zone = zone;
        this.nation = qNation;
        this.qcatressources=qcatressources;
        this.nomnav=nomnav;
        // this.qcatressources = qcatressources;
        this.qnavire = qnavire;
        this.typb = typb;
        this.dateDebutAuth = dateDebutAuth;
        this.dateFinAuth = dateFinAuth;
        this.anneeconstr = anneeconstr;
        this.balise = balise;
        this.calpoids = calpoids;
        this.count = count;
        this.eff = eff;
        this.gt = gt;
        this.imo = imo;
        this.modePeche=modePeche;
        this.kw = kw;
        this.larg = larg;
        this.longg = longg;
        this.nbrhomm = nbrhomm;
        this.nomar = nomar;
        this.numimm = this.qnavire.getNumimm();
        this.numlic = numlic;
        this.port = port;
        this.puimot = puimot;
        this.radio = radio;
        this.tjb = tjb;
        this.modePeche=modePeche;
    }

    public qTypeLic getQtypnav() {
        return qtypnav;
    }

    public void setQtypnav(qTypeLic qtypnav) {
        this.qtypnav = qtypnav;
    }



    public qZone getZone() {
        return zone;
    }

    public void setZone(qZone zone) {
        this.zone = zone;
    }

    public qNation getqNation() {
        return nation;
    }

    public void setqNation(qNation qNation) {
        this.nation = qNation;
    }

    public List<qCategRessource> getQcatressources() {
        return qcatressources;
    }

    public void setQcatressources(List<qCategRessource> qcatressources) {
        this.qcatressources=qcatressources;
    }

    public qNavireLegale getQnavire() {
        return qnavire;
    }

    public void setQnavire(qNavireLegale qnavire) {
        this.qnavire = qnavire;
    }

    public enumTypeBat getTypb() {
        return typb;
    }

    public void setTypb(enumTypeBat typb) {
        this.typb = typb;
    }

    public Date getDateDebutAuth() {
        return dateDebutAuth;
    }

    public void setDateDebutAuth(Date dateDebutAuth) {
        this.dateDebutAuth = dateDebutAuth;
    }

    public Date getDateFinAuth() {
        return dateFinAuth;
    }

    public void setDateFinAuth(Date dateFinAuth) {
        this.dateFinAuth = dateFinAuth;
    }

    public Integer getAnneeconstr() {
        return anneeconstr;
    }

    public void setAnneeconstr(Integer anneeconstr) {
        this.anneeconstr = anneeconstr;
    }

    public String getBalise() {
        return balise;
    }

    public void setBalise(String balise) {
        this.balise = balise;
    }

    public String getCalpoids() {
        return calpoids;
    }

    public void setCalpoids(String calpoids) {
        this.calpoids = calpoids;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getEff() {
        return eff;
    }

    public void setEff(String eff) {
        this.eff = eff;
    }

    public float getGt() {
        return gt;
    }

    public void setGt(float gt) {
        this.gt = gt;
    }

    public Integer getImo() {
        return imo;
    }

    public void setImo(Integer imo) {
        this.imo = imo;
    }

    public float getKw() {
        return kw;
    }

    public void setKw(float kw) {
        this.kw = kw;
    }

    public String getLarg() {
        return larg;
    }

    public void setLarg(String larg) {
        this.larg = larg;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getNbrhomm() {
        return nbrhomm;
    }

    public void setNbrhomm(String nbrhomm) {
        this.nbrhomm = nbrhomm;
    }

    public String getNomar() {
        return nomar;
    }

    public void setNomar(String nomar) {
        this.nomar = nomar;
    }


    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public String getNumlic() {
        return numlic;
    }

    public void setNumlic(String numlic) {
        this.numlic = numlic;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPuimot() {
        return puimot;
    }

    public void setPuimot(String puimot) {
        this.puimot = puimot;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public float getTjb() {
        return tjb;
    }

    public void setTjb(float tjb) {
        this.tjb = tjb;
    }

    @Override
    public String toString() {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date rr=null,tt=null;
        try {
           rr=formatter.parse(formatter.format(dateDebutAuth));
           tt=formatter.parse(formatter.format(dateFinAuth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Numéro:" + numlic + " de :"+dateDebutAuth +" a "+dateFinAuth;

    }
}