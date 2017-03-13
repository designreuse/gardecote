package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/10/2016.
 */

@Entity
@Table(name="qCategoriesRessourcesPeche", schema="dbo", catalog="GCM8" )
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
   @ManyToMany(mappedBy = "qcatressources",targetEntity = qLic.class,cascade = CascadeType.MERGE)
  //  @JoinColumn(insertable = false,updatable = false)
    private List<qLic>    qlicences;

    @ManyToMany(mappedBy = "qcatressources",targetEntity = qNavireLegale.class,cascade = CascadeType.MERGE)
    //  @JoinColumn(insertable = false,updatable = false)
    private List<qNavireLegale>    qNavires;

    @ManyToMany(targetEntity =qEnginsLicence.class,cascade = CascadeType.ALL)
    @JoinTable(name = "qAssocCategRessourcesEngins")
    @JsonManagedReference
    private List<qEnginsLicence> Engins;

    @OneToOne
    private qTypeLic ancienCategoriePeche;

    @ManyToMany(mappedBy = "categoriesRessources" ,  targetEntity =qConcession.class,cascade = CascadeType.MERGE)
   // @JoinColumn(name = "ref_concessionfk", referencedColumnName = "ref_concession",insertable = false,updatable = false)

   // @JoinColumn(name = "ref_concession", nullable = false)
    @JsonManagedReference
    private List<qConcession> qconcession;

    @OneToOne
    @JsonManagedReference
    private  qTypeConcession typeconcessionConcernee;


    public List<qLic> getQlicences() {
        return qlicences;
    }

    public void setQlicences(List<qLic> qlicences) {
        this.qlicences = qlicences;
    }

    public qCategRessource(qTypeConcession typeConcession, enumSupport typeSupport, List<qLic>  licences, List<qEnginsLicence> engins,qTypeLic ancienCategoriePeche) {
        this.idtypeConcession = typeConcession.getQtypeconcessionpk();
        this.qlicences=licences;
        this.typeSupport = typeSupport;
        this.Engins = engins;
        this.typeconcessionConcernee=typeConcession;
        this.ancienCategoriePeche=ancienCategoriePeche;
   }

    public qTypeLic getAncienCategoriePeche() {
        return ancienCategoriePeche;
    }

    public void setAncienCategoriePeche(qTypeLic ancienCategoriePeche) {
        this.ancienCategoriePeche = ancienCategoriePeche;
    }

    public qCategRessource() {
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

    public List<qEnginsLicence> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginsLicence> engins) {
        Engins = engins;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCategRessource)) return false;

        qCategRessource that = (qCategRessource) o;

        return getIdtypeConcession().equals(that.getIdtypeConcession());

    }

    @Override
    public int hashCode() {
        return getIdtypeConcession().hashCode();
    }
}
