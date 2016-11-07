package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPeche", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPeche.countAll", query="SELECT COUNT(x) FROM qEnginPeche x" )
} )
public class qEnginPeche implements Serializable {
    @Id
   private enumEngin Engin;
    private Integer maillage;
    private boolean flage;

    @ManyToMany(mappedBy = "Engins",cascade = CascadeType.REFRESH)


    private List<qCategRessource> categressource;

    @ManyToMany(mappedBy = "qEngins")

   // @JoinColumns({
   //         @JoinColumn(name = "departfk", referencedColumnName = "depart",insertable = false,updatable = false),
    //        @JoinColumn(name = "numImmfk", referencedColumnName = "numImm",insertable = false,updatable = false)
    //})
    private List<qMarree> qmarrees;

    @ManyToMany(mappedBy = "Engins",cascade = CascadeType.REFRESH)
     private List<qLicence> qlicences;

    public qEnginPeche(enumEngin engin, Integer maillage) {
        Engin = engin;
        this.maillage = maillage;
    }

    public qEnginPeche(enumEngin engin, Integer maillage, List<qCategRessource> categressource) {

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
