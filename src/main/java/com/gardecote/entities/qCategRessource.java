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
    @NotNull
    private qTypeConcession typeConcession;

    private enumSupport typeSupport;
    @ManyToOne
    private qLicence    qlicence;

    @OneToMany(mappedBy = "categressource",targetEntity =qEnginPeche.class)
    private List<qEnginPeche> Engins;

    @ManyToOne
   // @JoinColumn(name = "ref_concession", nullable = false)
    private qConcession concession;

    public qCategRessource(qTypeConcession typeConcession, enumSupport typeSupport, List<qEnginPeche> engins) {
        this.typeConcession = typeConcession;
        this.typeSupport = typeSupport;
        Engins = engins;
    }

    public qCategRessource() {
    }

    public qTypeConcession getTypeConcession() {
        return typeConcession;
    }

    public void setTypeConcession(qTypeConcession typeConcession) {
        this.typeConcession = typeConcession;
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
