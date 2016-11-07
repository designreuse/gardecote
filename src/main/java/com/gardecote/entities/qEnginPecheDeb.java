package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPecheDeb", schema="dbo", catalog="GCM1" )
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

    @ManyToMany(mappedBy = "Engins",cascade = CascadeType.PERSIST)
 //   @JoinColumns({
  //          @JoinColumn(name = "departfk", referencedColumnName = "depart",insertable = false,updatable = false),
   //         @JoinColumn(name = "numImmfk", referencedColumnName = "numImm",insertable = false,updatable = false)
   // })
    private     List<qDebarquement> qdeb;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "qAssocEnginPecheDebCategRessources")
    private List<qCategRessource> categressource;

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

    public qEnginPecheDeb(enumEnginDeb engin, Integer maillage, List<qCategRessource> categressource) {

        Engin = engin;
        this.maillage = maillage;
        this.categressource = categressource;
    }

    public qEnginPecheDeb() {
    }

    public List<qDebarquement> getQdeb() {
        return qdeb;
    }

    public void setQdeb(List<qDebarquement> qdeb) {
        this.qdeb = qdeb;
    }

    public enumEnginDeb getEngin() {
        return Engin;
    }

    public void setEngin(enumEnginDeb engin) {
        Engin = engin;
    }

    public List<qCategRessource> getCategressource() {
        return categressource;
    }

    public void setCategressource(List<qCategRessource> categressource) {
        this.categressource = categressource;
    }

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }



}
