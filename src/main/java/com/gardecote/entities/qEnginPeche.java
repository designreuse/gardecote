package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPeche", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPeche.countAll", query="SELECT COUNT(x) FROM qEnginPeche x" )
} )
public class qEnginPeche implements Serializable {
    @Id
   private enumEngin Engin;
    private Integer maillage;
    private boolean flage;

    @ManyToOne


    private qCategRessource categressource;
    @ManyToOne
    private qEnginPecheDeb qdeb;
    public qEnginPeche(enumEngin engin, Integer maillage) {
        Engin = engin;
        this.maillage = maillage;
    }

    public qEnginPeche(enumEngin engin, Integer maillage, qCategRessource categressource) {

        Engin = engin;
        this.maillage = maillage;
        this.categressource = categressource;
    }

    public qEnginPeche() {
    }



    public enumEngin getEngin() {
        return Engin;
    }

    public void setEngin(enumEngin engin) {
        Engin = engin;
    }

    public qCategRessource getCategressource() {
        return categressource;
    }

    public void setCategressource(qCategRessource categressource) {
        this.categressource = categressource;
    }

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }
}
