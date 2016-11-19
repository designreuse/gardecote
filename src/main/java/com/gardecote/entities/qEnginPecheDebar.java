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
@Table(name="qEnginPecheDeb4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries ( {
        @NamedQuery ( name="qEnginPecheDebar.countAll", query="SELECT COUNT(x) FROM qEnginPecheDebar x" )
} )
@IdClass(qEnginPechePK.class)
public class qEnginPecheDebar implements Serializable {
    @Id
    private String numimm;
    @Id
    private Date dateDepart;
    @Id
    private enumEngin EnginMar;
    @Id
    private enumEnginDeb EnginDeb;

    @Column(name = "maillage")
    private Integer maillage;

    @Column(name = "flag")
    private boolean flag;



    @ManyToMany(mappedBy = "Engins")
    @JsonBackReference
    private     List<qDebarquement> qdeb;



    public qEnginPechePK getEnginPechePK(){
        qEnginPechePK engpk=new qEnginPechePK(this.numimm,this.dateDepart,null,this.EnginDeb);
        return engpk;
    }

    public qEnginPecheDebar(String numimm, Date dateDepart, enumEngin enginMar, enumEnginDeb enginDeb, Integer maillage, boolean flag) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        EnginMar = enginMar;
        EnginDeb = enginDeb;
        this.maillage = maillage;
        this.flag = flag;
    }




    public qEnginPecheDebar() {
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

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public List<qDebarquement> getQdeb() {
        return qdeb;
    }

    public void setQdeb(List<qDebarquement> qdeb) {
        this.qdeb = qdeb;
    }


}
