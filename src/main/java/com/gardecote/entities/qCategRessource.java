package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/10/2016.
 */

@Entity
@Table(name="qCategoriesRessources", schema="dbo", catalog="GCM1" )
// Define named queries here

@NamedQueries ( {
        @NamedQuery ( name="qCategRessource.countAll", query="SELECT COUNT(x) FROM qCategRessource x" )
} )

//@IdClass(qCategPK.class)
public class qCategRessource implements Serializable {
   @Id
   @Column(name = "idcateg")
   private Integer idtypeConcession;

   private enumSupport typeSupport;
   @ManyToMany(mappedBy = "qcatressources",cascade = CascadeType.REFRESH)
  //  @JoinColumn(insertable = false,updatable = false)
    private List<qLicence>    qlicences;

    @ManyToMany(targetEntity =qEnginPeche.class,cascade = CascadeType.MERGE)
    @JoinTable(name = "qAssocCategRessourcesEngins")
    private List<qEnginPeche> Engins;

    @ManyToMany(mappedBy = "categressource",targetEntity =qEnginPecheDeb.class,cascade = CascadeType.MERGE)

    private List<qEnginPecheDeb> EnginsDeb;

    @ManyToMany(mappedBy = "qcategconcernees",targetEntity =qDebarquement.class,cascade = CascadeType.MERGE)
  //  @JoinColumns({
 //          @JoinColumn(name = "departfk", referencedColumnName = "depart",insertable = false,updatable = false),
 //           @JoinColumn(name = "numImmfk", referencedColumnName = "numImm",insertable = false,updatable = false)
 //   })
    private List<qDebarquement> qdeb;

    @ManyToMany(targetEntity =qMarree.class)
  //  @JoinColumns({
   //         @JoinColumn(name = "departfkM", referencedColumnName = "depart",insertable = false,updatable = false),
     //       @JoinColumn(name = "numImmfkM", referencedColumnName = "numImm",insertable = false,updatable = false)
   // })
    @JoinTable(name = "qAssocMarreeCategRessources")
    private List<qMarree> qmarree;

    @ManyToMany(mappedBy = "categoriesRessources" ,  targetEntity =qConcession.class,cascade = CascadeType.MERGE)
   // @JoinColumn(name = "ref_concessionfk", referencedColumnName = "ref_concession",insertable = false,updatable = false)

   // @JoinColumn(name = "ref_concession", nullable = false)
    private List<qConcession> qconcession;

    @OneToOne
   private  qTypeConcession typeconcessionConcernee;

    public List<qLicence> getQlicences() {
        return qlicences;
    }

    public void setQlicences(List<qLicence> qlicences) {
        this.qlicences = qlicences;
    }

    public qCategRessource(qTypeConcession typeConcession, enumSupport typeSupport,List<qLicence>  lic, List<qEnginPeche> engins) {
        this.idtypeConcession = typeConcession.getQtypeconcessionpk();
        this.qlicences=lic;
        this.typeSupport = typeSupport;
        this.Engins = engins;
        this.typeconcessionConcernee=typeConcession;
   }



    public qCategRessource() {
    }

    public List<qEnginPecheDeb> getEnginsDeb() {
        return EnginsDeb;
    }

    public void setEnginsDeb(List<qEnginPecheDeb> enginsDeb) {
        EnginsDeb = enginsDeb;
    }

    public Integer getIdtypeConcession() {
        return idtypeConcession;
    }

    public void setIdtypeConcession(Integer idtypeConcession) {
        this.idtypeConcession = idtypeConcession;
    }

    public enumSupport getTypeSupport() {
        return typeSupport;
    }

    public void setTypeSupport(enumSupport typeSupport) {
        this.typeSupport = typeSupport;
    }

    public List<qEnginPeche> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPeche> engins) {
        Engins = engins;
    }

    public List<qDebarquement> getQdeb() {
        return qdeb;
    }

    public void setQdeb(List<qDebarquement> qdeb) {
        this.qdeb = qdeb;
    }

    public List<qMarree> getQmarree() {
        return qmarree;
    }

    public void setQmarree(List<qMarree> qmarree) {
        this.qmarree = qmarree;
    }

    public List<qConcession> getQconcession() {
        return qconcession;
    }

    public void setQconcession(List<qConcession> qconcession) {
        this.qconcession = qconcession;
    }

    public qTypeConcession getTypeconcessionConcernee() {
        return typeconcessionConcernee;
    }

    public void setTypeconcessionConcernee(qTypeConcession typeconcessionConcernee) {
        this.typeconcessionConcernee = typeconcessionConcernee;
    }
}
