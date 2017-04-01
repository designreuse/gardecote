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
    @Column(name="numPage", nullable=false, length=50)
    private String     numeroPage;
    @Id
    @Column(name="typeDoc", nullable=false, length=50)
    private enumTypeDoc     typeDoc;

    @Id
    private Integer numOrdre;

    @OneToOne(targetEntity = qEspeceTypee.class)
    private qEspeceTypee masterEspeceTypee;
    @OneToOne(targetEntity = qEspece.class)
    private qEspece   especeChoisie;
    @ManyToOne
    private qPageMarree  pagemaree;
    @ManyToOne
    private qPageDebarquement  pagedebarquement;

    public qEspeceDynamicPK getQEspeceDynamicPK(){
        qEspeceDynamicPK espPK=new qEspeceDynamicPK(numeroPage,typeDoc,numOrdre);
        return espPK;
    }
    public qEspeceDynamic() {
        super();
    }

    public qEspeceDynamic(String numeroPage, enumTypeDoc typeDoc, Integer numOrdre, qEspeceTypee masterEspeceTypee, qEspece especeChoisie) {
        this.numeroPage = numeroPage;
        this.typeDoc = typeDoc;
        this.numOrdre = numOrdre;
        this.masterEspeceTypee = masterEspeceTypee;
        this.especeChoisie = especeChoisie;
    }

    public qPageMarree getPagemaree() {
        return pagemaree;
    }

    public void setPagemaree(qPageMarree pagemaree) {
        this.pagemaree = pagemaree;
    }

    public qPageDebarquement getPagedebarquement() {
        return pagedebarquement;
    }

    public void setPagedebarquement(qPageDebarquement pagedebarquement) {
        this.pagedebarquement = pagedebarquement;
    }

    public String getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(String numeroPage) {
        this.numeroPage = numeroPage;
    }

    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
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
