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
   private Integer IdEngin;

    private String Engin;
    private Integer maillage;

    @ManyToOne
    private qCategRessource categressource;

    public qEnginPeche(String engin, Integer maillage) {
        Engin = engin;
        this.maillage = maillage;
    }

    public qEnginPeche(Integer idEngin, String engin, Integer maillage, qCategRessource categressource) {
        IdEngin = idEngin;
        Engin = engin;
        this.maillage = maillage;
        this.categressource = categressource;
    }

    public qEnginPeche() {
    }

    public Integer getIdEngin() {
        return IdEngin;
    }

    public void setIdEngin(Integer idEngin) {
        IdEngin = idEngin;
    }

    public String getEngin() {
        return Engin;
    }

    public void setEngin(String engin) {
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
