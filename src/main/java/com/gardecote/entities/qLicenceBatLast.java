/*
 * Created on 8 oct. 2016 ( Time 01:00:05 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "licences_bat_last"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qlicencebatlast", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="qLicencesBatLast.countAll", query="SELECT COUNT(x) FROM  qLicenceBatLast x")
} )
public class qLicenceBatLast implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_lic", nullable=false)
    private Long idLic        ;

     @ManyToOne
     private qRegistreNavire qnavire;
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="ancons", length=255)
    private String     ancons       ;

    @Column(name="balise")
    private Integer    balise       ;

    @Column(name="calpoids", length=255)
    private String     calpoids     ;

    @Column(name="code_type_supp_droit", length=50)
    private String     codeTypeSuppDroit ;

    @Column(name="consignataire")
    private Integer    consignataire ;

    @Column(name="count", length=255)
    private String     count        ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="debaut")
    private Date       debaut       ;

    @Column(name="eff", length=255)
    private String     eff          ;

    @Column(name="engin", length=255)
    private String     engin        ;

    @Column(name="engin1", length=255)
    private String     engin1       ;

    @Column(name="engin2", length=255)
    private String     engin2       ;

    @Column(name="engin3", length=255)
    private String     engin3       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="finauto")
    private Date       finauto      ;

    @Column(name="gt")
    private Double     gt           ;

    @Column(name="id_concessionnaire", length=50)
    private String     idConcessionnaire ;

    @Column(name="id_nation", nullable=false)
    private Integer    idNation     ;

    @Column(name="id_segment_peche", length=50)
    private String     idSegmentPeche ;

    @Column(name="id_type_concession", length=50)
    private String     idTypeConcession ;

    @Column(name="id_type_lic", nullable=false)
    private qTypeLic    idTypeLic    ;

    @Column(name="id_zone")
    private qZone   idZone       ;

    @Column(name="imo")
    private Integer    imo          ;

    @Column(name="kw")
    private Double     kw           ;

    @Column(name="larg", length=255)
    private String     larg         ;

    @Column(name="longg", length=255)
    private String     longg        ;

    @Column(name="mail", length=255)
    private String     mail         ;

    @Column(name="mail1", length=255)
    private String     mail1        ;

    @Column(name="mail2", length=255)
    private String     mail2        ;

    @Column(name="mail3", length=255)
    private String     mail3        ;

    @Column(name="mintyplic", length=255)
    private String     mintyplic    ;

    @Column(name="mintypnav", length=255)
    private String     mintypnav    ;

    @Column(name="nation1", length=255)
    private String     nation1      ;

    @Column(name="nbrhomm", length=255)
    private String     nbrhomm      ;

    @Column(name="nomar", length=255)
    private String     nomar        ;

    @Column(name="nomex", length=255)
    private String     nomex        ;

    @Column(name="nomnav", length=255)
    private String     nomnav       ;

    @Column(name="numenr", length=255)
    private String     numenr       ;

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

    @Column(name="ref_contrat_concession", length=50)
    private String     refContratConcession ;

    @Column(name="tjb")
    private Double     tjb          ;

    @Column(name="typauto", length=255)
    private String     typauto      ;

    @Column(name="typb")
    private Integer    typb         ;

    @Column(name="typencad")
    private qTypeEnc    typencad     ;

   //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public qLicenceBatLast()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdLic( Long idLic )
    {
        this.idLic = idLic ;
    }
    public Long getIdLic()
    {
        return this.idLic;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : ancons ( varchar ) 
    public void setAncons( String ancons )
    {
        this.ancons = ancons;
    }
    public String getAncons()
    {
        return this.ancons;
    }

    //--- DATABASE MAPPING : balise ( int ) 
    public void setBalise( Integer balise )
    {
        this.balise = balise;
    }
    public Integer getBalise()
    {
        return this.balise;
    }

    //--- DATABASE MAPPING : calpoids ( varchar ) 
    public void setCalpoids( String calpoids )
    {
        this.calpoids = calpoids;
    }
    public String getCalpoids()
    {
        return this.calpoids;
    }

    //--- DATABASE MAPPING : code_type_supp_droit ( varchar ) 
    public void setCodeTypeSuppDroit( String codeTypeSuppDroit )
    {
        this.codeTypeSuppDroit = codeTypeSuppDroit;
    }
    public String getCodeTypeSuppDroit()
    {
        return this.codeTypeSuppDroit;
    }

    //--- DATABASE MAPPING : consignataire ( int ) 
    public void setConsignataire( Integer consignataire )
    {
        this.consignataire = consignataire;
    }
    public Integer getConsignataire()
    {
        return this.consignataire;
    }

    //--- DATABASE MAPPING : count ( varchar ) 
    public void setCount( String count )
    {
        this.count = count;
    }
    public String getCount()
    {
        return this.count;
    }

    //--- DATABASE MAPPING : debaut ( datetime ) 
    public void setDebaut( Date debaut )
    {
        this.debaut = debaut;
    }
    public Date getDebaut()
    {
        return this.debaut;
    }

    //--- DATABASE MAPPING : eff ( varchar ) 
    public void setEff( String eff )
    {
        this.eff = eff;
    }
    public String getEff()
    {
        return this.eff;
    }

    //--- DATABASE MAPPING : engin ( varchar ) 
    public void setEngin( String engin )
    {
        this.engin = engin;
    }
    public String getEngin()
    {
        return this.engin;
    }

    //--- DATABASE MAPPING : engin1 ( varchar ) 
    public void setEngin1( String engin1 )
    {
        this.engin1 = engin1;
    }
    public String getEngin1()
    {
        return this.engin1;
    }

    //--- DATABASE MAPPING : engin2 ( varchar ) 
    public void setEngin2( String engin2 )
    {
        this.engin2 = engin2;
    }
    public String getEngin2()
    {
        return this.engin2;
    }

    //--- DATABASE MAPPING : engin3 ( varchar ) 
    public void setEngin3( String engin3 )
    {
        this.engin3 = engin3;
    }
    public String getEngin3()
    {
        return this.engin3;
    }

    //--- DATABASE MAPPING : finauto ( datetime ) 
    public void setFinauto( Date finauto )
    {
        this.finauto = finauto;
    }
    public Date getFinauto()
    {
        return this.finauto;
    }

    //--- DATABASE MAPPING : gt ( float ) 
    public void setGt( Double gt )
    {
        this.gt = gt;
    }
    public Double getGt()
    {
        return this.gt;
    }

    //--- DATABASE MAPPING : id_concessionnaire ( varchar ) 
    public void setIdConcessionnaire( String idConcessionnaire )
    {
        this.idConcessionnaire = idConcessionnaire;
    }
    public String getIdConcessionnaire()
    {
        return this.idConcessionnaire;
    }

    //--- DATABASE MAPPING : id_nation ( int ) 
    public void setIdNation( Integer idNation )
    {
        this.idNation = idNation;
    }
    public Integer getIdNation()
    {
        return this.idNation;
    }

    //--- DATABASE MAPPING : id_segment_peche ( varchar ) 
    public void setIdSegmentPeche( String idSegmentPeche )
    {
        this.idSegmentPeche = idSegmentPeche;
    }
    public String getIdSegmentPeche()
    {
        return this.idSegmentPeche;
    }

    //--- DATABASE MAPPING : id_type_concession ( varchar ) 
    public void setIdTypeConcession( String idTypeConcession )
    {
        this.idTypeConcession = idTypeConcession;
    }
    public String getIdTypeConcession()
    {
        return this.idTypeConcession;
    }

    //--- DATABASE MAPPING : id_type_lic ( int ) 
    public void setIdTypeLic( qTypeLic idTypeLic )
    {
        this.idTypeLic = idTypeLic;
    }
    public qTypeLic getIdTypeLic()
    {
        return this.idTypeLic;
    }

    //--- DATABASE MAPPING : id_zone ( int ) 
    public void setIdZone( qZone idZone )
    {
        this.idZone = idZone;
    }
    public qZone getIdZone()
    {
        return this.idZone;
    }

    //--- DATABASE MAPPING : imo ( int ) 
    public void setImo( Integer imo )
    {
        this.imo = imo;
    }
    public Integer getImo()
    {
        return this.imo;
    }

    //--- DATABASE MAPPING : kw ( float ) 
    public void setKw( Double kw )
    {
        this.kw = kw;
    }
    public Double getKw()
    {
        return this.kw;
    }

    //--- DATABASE MAPPING : larg ( varchar ) 
    public void setLarg( String larg )
    {
        this.larg = larg;
    }
    public String getLarg()
    {
        return this.larg;
    }

    //--- DATABASE MAPPING : longg ( varchar ) 
    public void setLongg( String longg )
    {
        this.longg = longg;
    }
    public String getLongg()
    {
        return this.longg;
    }

    //--- DATABASE MAPPING : mail ( varchar ) 
    public void setMail( String mail )
    {
        this.mail = mail;
    }
    public String getMail()
    {
        return this.mail;
    }

    //--- DATABASE MAPPING : mail1 ( varchar ) 
    public void setMail1( String mail1 )
    {
        this.mail1 = mail1;
    }
    public String getMail1()
    {
        return this.mail1;
    }

    //--- DATABASE MAPPING : mail2 ( varchar ) 
    public void setMail2( String mail2 )
    {
        this.mail2 = mail2;
    }
    public String getMail2()
    {
        return this.mail2;
    }

    //--- DATABASE MAPPING : mail3 ( varchar ) 
    public void setMail3( String mail3 )
    {
        this.mail3 = mail3;
    }
    public String getMail3()
    {
        return this.mail3;
    }

    //--- DATABASE MAPPING : mintyplic ( varchar ) 
    public void setMintyplic( String mintyplic )
    {
        this.mintyplic = mintyplic;
    }
    public String getMintyplic()
    {
        return this.mintyplic;
    }

    //--- DATABASE MAPPING : mintypnav ( varchar ) 
    public void setMintypnav( String mintypnav )
    {
        this.mintypnav = mintypnav;
    }
    public String getMintypnav()
    {
        return this.mintypnav;
    }

    //--- DATABASE MAPPING : nation1 ( varchar ) 
    public void setNation1( String nation1 )
    {
        this.nation1 = nation1;
    }
    public String getNation1()
    {
        return this.nation1;
    }

    //--- DATABASE MAPPING : nbrhomm ( varchar ) 
    public void setNbrhomm( String nbrhomm )
    {
        this.nbrhomm = nbrhomm;
    }
    public String getNbrhomm()
    {
        return this.nbrhomm;
    }

    //--- DATABASE MAPPING : nomar ( varchar ) 
    public void setNomar( String nomar )
    {
        this.nomar = nomar;
    }
    public String getNomar()
    {
        return this.nomar;
    }

    //--- DATABASE MAPPING : nomex ( varchar ) 
    public void setNomex( String nomex )
    {
        this.nomex = nomex;
    }
    public String getNomex()
    {
        return this.nomex;
    }

    //--- DATABASE MAPPING : nomnav ( varchar ) 
    public void setNomnav( String nomnav )
    {
        this.nomnav = nomnav;
    }
    public String getNomnav()
    {
        return this.nomnav;
    }

    //--- DATABASE MAPPING : numenr ( varchar ) 
    public void setNumenr( String numenr )
    {
        this.numenr = numenr;
    }
    public String getNumenr()
    {
        return this.numenr;
    }

    //--- DATABASE MAPPING : numimm ( varchar ) 
    public void setNumimm( String numimm )
    {
        this.numimm = numimm;
    }
    public String getNumimm()
    {
        return this.numimm;
    }

    //--- DATABASE MAPPING : numlic ( varchar ) 
    public void setNumlic( String numlic )
    {
        this.numlic = numlic;
    }
    public String getNumlic()
    {
        return this.numlic;
    }

    //--- DATABASE MAPPING : port ( varchar ) 
    public void setPort( String port )
    {
        this.port = port;
    }
    public String getPort()
    {
        return this.port;
    }

    //--- DATABASE MAPPING : puimot ( varchar ) 
    public void setPuimot( String puimot )
    {
        this.puimot = puimot;
    }
    public String getPuimot()
    {
        return this.puimot;
    }

    //--- DATABASE MAPPING : radio ( varchar ) 
    public void setRadio( String radio )
    {
        this.radio = radio;
    }
    public String getRadio()
    {
        return this.radio;
    }

    //--- DATABASE MAPPING : ref_contrat_concession ( varchar ) 
    public void setRefContratConcession( String refContratConcession )
    {
        this.refContratConcession = refContratConcession;
    }
    public String getRefContratConcession()
    {
        return this.refContratConcession;
    }

    //--- DATABASE MAPPING : tjb ( float ) 
    public void setTjb( Double tjb )
    {
        this.tjb = tjb;
    }
    public Double getTjb()
    {
        return this.tjb;
    }

    //--- DATABASE MAPPING : typauto ( varchar ) 
    public void setTypauto( String typauto )
    {
        this.typauto = typauto;
    }
    public String getTypauto()
    {
        return this.typauto;
    }

    //--- DATABASE MAPPING : typb ( int ) 
    public void setTypb( Integer typb )
    {
        this.typb = typb;
    }
    public Integer getTypb()
    {
        return this.typb;
    }

    //--- DATABASE MAPPING : typencad ( int ) 
    public void setTypencad( qTypeEnc typencad )
    {
        this.typencad = typencad;
    }
    public qTypeEnc getTypencad()
    {
        return this.typencad;
    }




    @ManyToOne
    @JoinColumn(name="Ref_concession", referencedColumnName="Ref_concession")
    private qConcession concession;
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idLic);
        sb.append("]:"); 
        sb.append(ancons);
        sb.append("|");
        sb.append(balise);
        sb.append("|");
        sb.append(calpoids);
        sb.append("|");
        sb.append(codeTypeSuppDroit);
        sb.append("|");
        sb.append(consignataire);
        sb.append("|");
        sb.append(count);
        sb.append("|");
        sb.append(debaut);
        sb.append("|");
        sb.append(eff);
        sb.append("|");
        sb.append(engin);
        sb.append("|");
        sb.append(engin1);
        sb.append("|");
        sb.append(engin2);
        sb.append("|");
        sb.append(engin3);
        sb.append("|");
        sb.append(finauto);
        sb.append("|");
        sb.append(gt);
        sb.append("|");
        sb.append(idConcessionnaire);
        sb.append("|");
        sb.append(idNation);
        sb.append("|");
        sb.append(idSegmentPeche);
        sb.append("|");
        sb.append(idTypeConcession);
        sb.append("|");
        sb.append(idTypeLic);
        sb.append("|");
        sb.append(idZone);
        sb.append("|");
        sb.append(imo);
        sb.append("|");
        sb.append(kw);
        sb.append("|");
        sb.append(larg);
        sb.append("|");
        sb.append(longg);
        sb.append("|");
        sb.append(mail);
        sb.append("|");
        sb.append(mail1);
        sb.append("|");
        sb.append(mail2);
        sb.append("|");
        sb.append(mail3);
        sb.append("|");
        sb.append(mintyplic);
        sb.append("|");
        sb.append(mintypnav);
        sb.append("|");
        sb.append(nation1);
        sb.append("|");
        sb.append(nbrhomm);
        sb.append("|");
        sb.append(nomar);
        sb.append("|");
        sb.append(nomex);
        sb.append("|");
        sb.append(nomnav);
        sb.append("|");
        sb.append(numenr);
        sb.append("|");
        sb.append(numimm);
        sb.append("|");
        sb.append(numlic);
        sb.append("|");
        sb.append(port);
        sb.append("|");
        sb.append(puimot);
        sb.append("|");
        sb.append(radio);
        sb.append("|");
        sb.append(refContratConcession);
        sb.append("|");
        sb.append(tjb);
        sb.append("|");
        sb.append(typauto);
        sb.append("|");
        sb.append(typb);
        sb.append("|");
        sb.append(typencad);
        sb.append("|");

        return sb.toString(); 
    } 

}