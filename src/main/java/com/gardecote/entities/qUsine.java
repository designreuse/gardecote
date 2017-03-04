package com.gardecote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 25/10/2016.
 */
@Entity
@Table(name="qUsine", schema="dbo", catalog="GCM5" )
// Define named queries here
@NamedQueries( {
        @NamedQuery( name="qUsine.countAll", query="SELECT COUNT(x) FROM qUsine x" )
} )
public class qUsine implements Serializable {
    @Id
    private String refAgrement;
    private String refAgrementUE;
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

    public qUsine() {
    }

    public qUsine(String refUsine, String refEuropean, String capaciteStockage, String capaciteCongelation, String nomUsine, String address, String nomResp, String lieuImplementation, boolean signatureCapitaine) {
        this.refAgrement = refUsine;
        this.refAgrementUE = refEuropean;
        this.capaciteStockage = capaciteStockage;
        this.capaciteCongelation = capaciteCongelation;
        this.nomUsine = nomUsine;
        this.address = address;
        this.nomResp = nomResp;
        this.lieuImplementation = lieuImplementation;
        this.signatureCapitaine = signatureCapitaine;
    }



    public List<qCarnet> getQcarnets() {
        return qcarnets;
    }

    public void setQcarnets(List<qCarnet> qcarnets) {
        this.qcarnets = qcarnets;
    }


    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public String getRefAgrementUE() {
        return refAgrementUE;
    }

    public void setRefAgrementUE(String refAgrementUE) {
        this.refAgrementUE = refAgrementUE;
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
