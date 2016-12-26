package com.gardecote.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Dell on 20/12/2016.
 */
@Entity
@Table(name="qQteTraites20", schema="dbo", catalog="GCM1" )
@IdClass(qDocPK.class)
public class qQuantitesTraites {
    @Id
    String numImm;
    @Id
    Date depart;
    @OneToOne
    private qTraitement traitement;

    private Integer congelee;
    private  Integer frais;
    private  Integer elaboree;
    private Integer transformee;
    private Integer fini;
    private Integer huile;

    public qQuantitesTraites(qTraitement tr,Integer congelee, Integer frais, Integer elaboree, Integer transformee, Integer fini, Integer huile) {
        this.numImm=tr.getNumImm();
        this.depart=tr.getDepart();
        this.congelee = congelee;
        this.frais = frais;
        this.elaboree = elaboree;
        this.transformee = transformee;
        this.fini = fini;
        this.huile = huile;
        this.traitement=tr;
    }


    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public qTraitement getTraitement() {
        return traitement;
    }

    public void setTraitement(qTraitement traitement) {
        this.traitement = traitement;
    }

    public Integer getCongelee() {
        return congelee;
    }

    public void setCongelee(Integer congelee) {
        this.congelee = congelee;
    }

    public Integer getFrais() {
        return frais;
    }

    public void setFrais(Integer frais) {
        this.frais = frais;
    }

    public Integer getElaboree() {
        return elaboree;
    }

    public void setElaboree(Integer elaboree) {
        this.elaboree = elaboree;
    }

    public Integer getTransformee() {
        return transformee;
    }

    public void setTransformee(Integer transformee) {
        this.transformee = transformee;
    }

    public Integer getFini() {
        return fini;
    }

    public void setFini(Integer fini) {
        this.fini = fini;
    }

    public Integer getHuile() {
        return huile;
    }

    public void setHuile(Integer huile) {
        this.huile = huile;
    }
}
