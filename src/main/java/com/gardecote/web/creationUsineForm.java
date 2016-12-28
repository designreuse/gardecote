package com.gardecote.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qUsine;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 27/12/2016.
 */
public class creationUsineForm  implements Serializable {
    private qUsine currentusine;
    private String refAgrement;
    private String refAgrementUE;
    private String capaciteStockage;
    private String capaciteCongelation;
    private String nomUsine;
    private String address;
    private String nomResp;
    private String lieuImplementation;
    private boolean signatureCapitaine;

    public String getRefAgrement() {
        return refAgrement;
    }

    public qUsine getCurrentusine() {
        return currentusine;
    }

    public void setCurrentusine(qUsine currentusine) {
        this.currentusine = currentusine;
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
