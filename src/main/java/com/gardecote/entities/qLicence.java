/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
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
 * Persistent class for entity stored in table "licences_bat_last"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qlicence", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qLicence.countAll", query="SELECT COUNT(x) FROM  qLicence x")
} )
public class qLicence implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_lic", nullable=false)
    private Long idLic        ;

   // pour la compatibilté avec les anciens données

    @OneToOne
    //@Column(name="id_type_nav")
    private qTypeLic   qtypnav       ;
    //@OneToOne
   // @Column(name="typencad")
    private qTypeEnc    typencad     ;
    @OneToOne
  //  @Column(name="id_zone")
    private qZone   zone       ;
    @OneToOne
  //  @Column(name="id_nation")
    private qNation    nation     ;

    // Ca c'est pour le format de nouvelle strategie

    @ManyToMany(cascade = CascadeType.REFRESH,targetEntity =qCategRessource.class,fetch = FetchType.EAGER)
    @JoinTable(name = "qAssocLicencesCategRessources")
    private List<qCategRessource> qcatressources;



    @ManyToMany(cascade = CascadeType.REFRESH,targetEntity =qEnginPeche.class,fetch = FetchType.EAGER)
    @JoinTable(name = "qAssocLicencesEngins")
    private List<qEnginPeche> Engins;

    @Column(name="code_type_supp_droit", length=100)
    private enumSupport     typesuppDroit ;

    @ManyToOne
    private qRegistreNavire qnavire;
    @ManyToOne
    @JoinColumn(name="id_consignataire")
    private qConsignataire    qconcessionaire ;


    @ManyToOne
    @JoinColumn(name="qConcessionid")
    private qConcession     qconcession ;

    @Column(name="typb", length=100)
    private enumTypeBat    typb         ;

    // ca c'est la fin pour le format de nouvelle strategie

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="debaut")
    private Date       dateDebutAuth      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="finauto")
    private Date       dateFinAuth       ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="ancons", length=255)
    private Integer     anneeconstr       ;

    @Column(name="balise" , length=100)
    private Integer    balise       ;

    @Column(name="calpoids", length=255)
    private String     calpoids     ;


    @Column(name="count", length=255)
    private String     count        ;



    @Column(name="eff", length=255)
    private String     eff          ;



    @Column(name="gt")
    private float    gt           ;


    @Column(name="imo")
    private Integer    imo          ;

    @Column(name="kw" )
    private float    kw           ;

    @Column(name="larg", length=255)
    private String     larg         ;

    @Column(name="longg", length=255)
    private String     longg        ;



    @Column(name="nbrhomm", length=255)
    private String     nbrhomm      ;

    @Column(name="nomar", length=255)
    private String     nomar        ;

    @Column(name="nomex", length=255)
    private String     nomex        ;

    @Column(name="nomnav", length=255)
    private String     nomnav       ;


    @Column(name="numimm", nullable=false, length=255)
    private String     numimm       ;

    @Column(name="numlic", length=255)
    private String     numlic       ;

    @Column(name="port", length=255)
    private String     port         ;

    @Column(name="puimot", length=255)
    private String     puimot       ;

    @Column(name="radio", length=255)
    private String     radio        ;

   @Column(name="tjb")
    private float     tjb          ;
//----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qLicence()
    {
		super();
    }

    public qLicence(qTypeLic qtypnav, qTypeEnc typencad, qZone zone, com.gardecote.entities.qNation qNation, List<qCategRessource> qcatressources, List<qEnginPeche> engins, enumSupport typesuppDroit, qRegistreNavire qnavire, qConsignataire qconcessionaire, qConcession qconcession, enumTypeBat typb, Date dateDebutAuth, Date dateFinAuth, Integer anneeconstr, Integer balise, String calpoids, String count, String eff, float gt, Integer imo, float kw, String larg, String longg, String nbrhomm, String nomar, String nomex, String nomnav, String numimm, String numlic, String port, String puimot, String radio, float tjb) {

        this.qtypnav = qtypnav;
        this.typencad = typencad;
        this.zone = zone;
        this.nation = qNation;
        this.qcatressources=qcatressources;
       // this.qcatressources = qcatressources;
        this.Engins = engins;
        this.typesuppDroit = typesuppDroit;
        this.qnavire = qnavire;
        this.qconcessionaire = qconcessionaire;
        this.qconcession = qconcession;
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
        this.kw = kw;
        this.larg = larg;
        this.longg = longg;
        this.nbrhomm = nbrhomm;
        this.nomar = nomar;
        this.nomex = nomex;
        this.nomnav = nomnav;
        this.numimm = numimm;
        this.numlic = numlic;
        this.port = port;
        this.puimot = puimot;
        this.radio = radio;
        this.tjb = tjb;
    }

    public Long getIdLic() {
        return idLic;
    }

    public void setIdLic(Long idLic) {
        this.idLic = idLic;
    }

    public qTypeLic getQtypnav() {
        return qtypnav;
    }

    public void setQtypnav(qTypeLic qtypnav) {
        this.qtypnav = qtypnav;
    }

    public qTypeEnc getTypencad() {
        return typencad;
    }

    public void setTypencad(qTypeEnc typencad) {
        this.typencad = typencad;
    }

    public qZone getZone() {
        return zone;
    }

    public void setZone(qZone zone) {
        this.zone = zone;
    }

    public com.gardecote.entities.qNation getqNation() {
        return nation;
    }

    public void setqNation(com.gardecote.entities.qNation qNation) {
        this.nation = qNation;
    }

    public List<qCategRessource> getQcatressources() {
        return qcatressources;
    }

    public void setQcatressources(List<qCategRessource> qcatressources) {

        this.qcatressources=qcatressources;

    }

    public List<qEnginPeche> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPeche> engins) {
        Engins = engins;
    }

    public enumSupport getTypesuppDroit() {
        return typesuppDroit;
    }

    public void setTypesuppDroit(enumSupport typesuppDroit) {
        this.typesuppDroit = typesuppDroit;
    }

    public qRegistreNavire getQnavire() {
        return qnavire;
    }

    public void setQnavire(qRegistreNavire qnavire) {
        this.qnavire = qnavire;
    }

    public qConsignataire getQconcessionaire() {
        return qconcessionaire;
    }

    public void setQconcessionaire(qConsignataire qconcessionaire) {
        this.qconcessionaire = qconcessionaire;
    }

    public qConcession getQconcession() {
        return qconcession;
    }

    public void setQconcession(qConcession qconcession) {
        this.qconcession = qconcession;
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

    public Integer getBalise() {
        return balise;
    }

    public void setBalise(Integer balise) {
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

    public String getNomex() {
        return nomex;
    }

    public void setNomex(String nomex) {
        this.nomex = nomex;
    }

    public String getNomnav() {
        return nomnav;
    }

    public void setNomnav(String nomnav) {
        this.nomnav = nomnav;
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
}