package com.gardecote.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/10/2016.
 */

@Entity
@Table(name="qCategoriesRessources", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here

@NamedQueries ( {
        @NamedQuery ( name="qCategRessource.countAll", query="SELECT COUNT(x) FROM qCategRessource x" )
} )


public class qCategRessource implements Serializable {
   @Id
   // private qTypeConcession idTypeConcession;
  private Integer idtypeConcession;

    private enumSupport typeSupport;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private qLicence    qlicence;

    @OneToMany(mappedBy = "categressource",targetEntity =qEnginPeche.class,cascade = CascadeType.PERSIST)

    private List<qEnginPeche> Engins;

    @ManyToOne
   // @JoinColumn(name = "ref_concession", nullable = false)
    private qConcession concession;

    @OneToOne(cascade = CascadeType.PERSIST)
   private  qTypeConcession typeconcessionConcernee;

    public qLicence getQlicence() {
        return qlicence;
    }

    public void setQlicence(qLicence qlicence) {
        this.qlicence = qlicence;
    }

    public qCategRessource(qTypeConcession typeConcession, enumSupport typeSupport, List<qEnginPeche> engins) {

        this.typeSupport = typeSupport;
        this.Engins = engins;
        this.typeconcessionConcernee=typeConcession;
        this.idtypeConcession = typeConcession.getQtypeconcessionpk();
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

    public List<qEnginPeche> getEngins() {
        return Engins;
    }

    public void setEngins(List<qEnginPeche> engins) {
        Engins = engins;
    }

    public qConcession getConcession() {
        return concession;
    }

    public void setConcession(qConcession concession) {
        this.concession = concession;
    }

    public qTypeConcession getTypeconcessionConcernee() {
        return typeconcessionConcernee;
    }

    public void setTypeconcessionConcernee(qTypeConcession typeconcessionConcernee) {
        this.typeconcessionConcernee = typeconcessionConcernee;
    }
}
