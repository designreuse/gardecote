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
@Table(name="qlicence", schema="dbo", catalog="DSPCM_DB" )
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


    @Column(name="id_type_nav")
    private qTypeLic   qtypnav       ;

    @Column(name="typencad")
    private qTypeEnc    typencad     ;

    @Column(name="id_zone")
    private qZone   zone       ;

    @Column(name="id_nation", nullable=false)
    private qNation    qNation     ;

    @Column(name="typauto", length=255)
    private enumAuthorisation    modeAuto      ;



    // Ca c'est pour le format de nouvelle strategie

    @OneToMany(mappedBy = "categressource",targetEntity =qCategRessource.class)
    private List<qCategRessource> qcatressources;


    @OneToMany(mappedBy = "categressource",targetEntity =qEnginPeche.class)
    private List<qEnginPeche> Engins;

    @Column(name="code_type_supp_droit", length=50)
    private enumSupport     typesuppDroit ;

    @ManyToOne
    private qRegistreNavire qnavire;

    @Column(name="id_consignataire", length=50)
    private qConsignataire    qconsignataire ;


    @OneToOne
    private qConcession     qconcession ;

    @Column(name="typb")
    private enumTypeBat    typb         ;

    // ca c'est la fin pour le format de nouvelle strategie


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

    @Column(name="balise")
    private Integer    balise       ;

    @Column(name="calpoids", length=255)
    private String     calpoids     ;


    @Column(name="count", length=255)
    private String     count        ;

    @Temporal(TemporalType.TIMESTAMP)

    @Column(name="eff", length=255)
    private String     eff          ;



    @Column(name="gt")
    private Double     gt           ;


    @Column(name="imo")
    private Integer    imo          ;

    @Column(name="kw")
    private Double     kw           ;

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
    private Double     tjb          ;
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
    


}