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

//@IdClass(qTypeConcession.class)
public class qCategRessource implements Serializable {
    @Id
 private Long idTypeConcession;
  //  private qTypeConcession typeConcession;

    private enumSupport typeSupport;
    @ManyToOne
    private qLicence    qlicence;

    @OneToMany(mappedBy = "categressource",targetEntity =qEnginPeche.class)
    private List<qEnginPeche> Engins;

    @ManyToOne
   // @JoinColumn(name = "ref_concession", nullable = false)
    private qConcession concession;

    public qLicence getQlicence() {
        return qlicence;
    }

    public void setQlicence(qLicence qlicence) {
        this.qlicence = qlicence;
    }

    public qCategRessource(Long typeConcession, enumSupport typeSupport, List<qEnginPeche> engins) {
        this.idTypeConcession = typeConcession;
        this.typeSupport = typeSupport;
        Engins = engins;
    }

    public qCategRessource() {
    }

    public Long getTypeConcession() {
        return idTypeConcession;
    }

    public void setTypeConcession(Long typeConcession) {
        this.idTypeConcession = typeConcession;
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
}
