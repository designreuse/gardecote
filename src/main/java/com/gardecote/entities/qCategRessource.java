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

@IdClass(qCategRessourcePK.class)
public class qCategRessource implements Serializable {
    @Id
    @NotNull
    private enumSegPeche segPeche;
    @Id
    @NotNull
    private enumCategRessource categorie;

    private String typeConcession;

    private enumSupport typeSupport;

    @OneToMany(mappedBy = "categressource",targetEntity =qEnginPeche.class)
    private List<qEnginPeche> Engins;


    @ManyToOne
   // @JoinColumn(name = "ref_concession", nullable = false)
    private qConcession concession;

    public qCategRessourcePK getIdCR(){
        qCategRessourcePK gcr=new qCategRessourcePK();
        gcr.setCategorie(categorie);
        gcr.setSegPeche(segPeche);
        return gcr;
    }
    public enumSegPeche getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(enumSegPeche segPeche) {
        this.segPeche = segPeche;
    }

    public enumCategRessource getCategorie() {
        return categorie;
    }

    public void setCategorie(enumCategRessource categorie) {
        this.categorie = categorie;
    }

    public String getTypeConcession() {
        return typeConcession;
    }

    public void setTypeConcession(String typeConcession) {
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

    public qCategRessource(enumSegPeche segPeche, enumCategRessource categorie,  enumSupport typeSupport) {
        this.segPeche = segPeche;
        this.categorie = categorie;
        this.typeConcession = "segPeche" + "-"+ "categorie";
        this.typeSupport = typeSupport;


    }

    public qCategRessource() { super();
    }

    public void setConcession(qConcession concession) {
        this.concession = concession;
    }
}
