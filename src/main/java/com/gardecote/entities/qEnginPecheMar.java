package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * Created by Dell on 09/10/2016.
 */
@Entity
@Table(name="qEnginPeche4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPecheMar.countAll", query="SELECT COUNT(x) FROM qEnginPecheMar x" )
} )
@IdClass(qEnginPechePK.class)
public class qEnginPecheMar implements Serializable {
    @Id
    private String numimm;
    @Id
    private Date dateDepart;
    @Id
    private enumEngin EnginMar;
    @Id
    private enumEnginDeb EnginDeb;

    private Integer maillage;

    private boolean flage;

    @JsonBackReference
    @ManyToMany(mappedBy = "qEngins")
    private List<qMarree> qmarrees;




    public qEnginPecheMar(String numimm, Date dateDepart, enumEngin enginMar, enumEnginDeb enginDeb, Integer maillage, boolean flage) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        this.EnginMar = enginMar;
        this.EnginDeb = enginDeb;
        this.maillage = maillage;
        this.flage = flage;
    }

    public qEnginPecheMar() {
    }
   public  qEnginPechePK getEnginPechePK(){
        qEnginPechePK engpk=new qEnginPechePK(this.numimm,this.dateDepart,this.getEnginMar(),null);
       return engpk;
    }

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public enumEngin getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(enumEngin enginMar) {
        EnginMar = enginMar;
    }

    public enumEnginDeb getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(enumEnginDeb enginDeb) {
        EnginDeb = enginDeb;
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }


    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }
}
