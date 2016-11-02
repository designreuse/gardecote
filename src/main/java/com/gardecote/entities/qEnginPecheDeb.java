package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPecheDeb", schema="dbo", catalog="DSPCM_DB" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPecheDeb.countAll", query="SELECT COUNT(x) FROM qEnginPecheDeb x" )
} )
public class qEnginPecheDeb implements Serializable {
    @Id
    @Column(name="Engin", nullable=false)
    private enumEnginDeb Engin;
    @Column(name = "maillage")
    private Integer maillage;
    @Column(name = "flag")
    private boolean flag;

    @ManyToOne(cascade=CascadeType.ALL)
    private qDebarquement qdeb;
    @ManyToOne(cascade=CascadeType.ALL)
    private qMarree qmarree;

    @ManyToOne
    private qCategRessource categressource;

    public qEnginPecheDeb(enumEnginDeb engin,boolean flag, Integer maillage) {
        this.Engin = engin;
        this.flag=flag;
        this.maillage = maillage;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public qEnginPecheDeb(enumEnginDeb engin, Integer maillage, qCategRessource categressource) {

        Engin = engin;
        this.maillage = maillage;
        this.categressource = categressource;
    }

    public qEnginPecheDeb() {
    }



    public enumEnginDeb getEngin() {
        return Engin;
    }

    public void setEngin(enumEnginDeb engin) {
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
