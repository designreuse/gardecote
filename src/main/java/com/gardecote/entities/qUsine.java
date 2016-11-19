package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qUsine4", schema="dbo", catalog="GCM1" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qUsine.countAll", query="SELECT COUNT(x) FROM qUsine x" )
} )
public class qUsine implements Serializable {
    @Id
    private String refUsine;
    private String refEuropean;
    private String capaciteStockage;
    private String capaciteCongelation;
    private String nomUsine;
    private String address;
    private String nomResp;
    private String lieuImplementation;
    private boolean signatureCapitaine;

        @OneToMany(mappedBy="qusine", targetEntity=qCarnet.class)
        @JsonIgnore
    private List<qCarnet> qcarnets;
    @OneToMany(mappedBy = "qusine")
    private List<qTraitement>  traitements;

    public qUsine() {
    }

    public qUsine(String refUsine, String refEuropean, String capaciteStockage, String capaciteCongelation, String nomUsine, String address, String nomResp, String lieuImplementation, boolean signatureCapitaine) {
        this.refUsine = refUsine;
        this.refEuropean = refEuropean;
        this.capaciteStockage = capaciteStockage;
        this.capaciteCongelation = capaciteCongelation;
        this.nomUsine = nomUsine;
        this.address = address;
        this.nomResp = nomResp;
        this.lieuImplementation = lieuImplementation;
        this.signatureCapitaine = signatureCapitaine;
    }

    public List<qTraitement> getTraitements() {
        return traitements;
    }

    public void setTraitements(List<qTraitement> traitements) {
        this.traitements = traitements;
    }

    public List<qCarnet> getQcarnets() {
        return qcarnets;
    }

    public void setQcarnets(List<qCarnet> qcarnets) {
        this.qcarnets = qcarnets;
    }

    public String getRefUsine() {
        return refUsine;
    }

    public void setRefUsine(String refUsine) {
        this.refUsine = refUsine;
    }

    public String getRefEuropean() {
        return refEuropean;
    }

    public void setRefEuropean(String refEuropean) {
        this.refEuropean = refEuropean;
    }

    public String getCapaciteStockage() {
        return capaciteStockage;
    }

    public void setCapaciteStockage(String capaciteStockage) {
        this.capaciteStockage = capaciteStockage;
    }

    public String getCapaciteCongelation() {
        return capaciteCongelation;
    }

    public void setCapaciteCongelation(String capaciteCongelation) {
        this.capaciteCongelation = capaciteCongelation;
    }

    public String getNomUsine() {
        return nomUsine;
    }

    public void setNomUsine(String nomUsine) {
        this.nomUsine = nomUsine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNomResp() {
        return nomResp;
    }

    public void setNomResp(String nomResp) {
        this.nomResp = nomResp;
    }

    public String getLieuImplementation() {
        return lieuImplementation;
    }

    public void setLieuImplementation(String lieuImplementation) {
        this.lieuImplementation = lieuImplementation;
    }

    public boolean isSignatureCapitaine() {
        return signatureCapitaine;
    }

    public void setSignatureCapitaine(boolean signatureCapitaine) {
        this.signatureCapitaine = signatureCapitaine;
    }
}
