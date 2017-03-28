package com.gardecote.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 28/03/2017.
 */
@Entity
@Table(name="qEspeceDynamic", schema="dbo", catalog="GCM11")
@IdClass(qEspeceDynamicPK.class)
public class qEspeceDynamic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String numimm;

    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDepart;

    @Id
    private Integer numOrdre;

    @OneToOne(targetEntity = qEspeceTypee.class)
    private qEspeceTypee masterEspeceTypee;
    @OneToOne(targetEntity = qEspece.class)
    private qEspece   especeChoisie;
    @OneToOne(targetEntity = qMarree.class)
    private qMarree  maree;
    @OneToOne(targetEntity = qDebarquement.class)
    private qDebarquement  debarquement;

    public qEspeceDynamicPK getQEspeceDynamicPK(){
        qEspeceDynamicPK espPK=new qEspeceDynamicPK(numimm,dateDepart,numOrdre);
        return espPK;
    }
    public qEspeceDynamic() {
        super();
    }

    public qEspeceDynamic(String numimm, Date dateDepart, Integer numOrdre, qEspeceTypee masterEspeceTypee, qEspece especeChoisie) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        this.numOrdre = numOrdre;
        this.masterEspeceTypee = masterEspeceTypee;
        this.especeChoisie = especeChoisie;
    }

    public qMarree getMaree() {
        return maree;
    }

    public void setMaree(qMarree maree) {
        this.maree = maree;
    }

    public qDebarquement getDebarquement() {
        return debarquement;
    }

    public void setDebarquement(qDebarquement debarquement) {
        this.debarquement = debarquement;
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

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    public qEspeceTypee getMasterEspeceTypee() {
        return masterEspeceTypee;
    }

    public void setMasterEspeceTypee(qEspeceTypee masterEspeceTypee) {
        this.masterEspeceTypee = masterEspeceTypee;
    }

    public qEspece getEspeceChoisie() {
        return especeChoisie;
    }

    public void setEspeceChoisie(qEspece especeChoisie) {
        this.especeChoisie = especeChoisie;
    }
}
