package com.gardecote.web;

import com.gardecote.entities.enumTypeDoc;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 24/12/2016.
 */
public class frmAnnexe implements Serializable {
    private String prefixeAnnexe;
    private String nomnavAnnexe;
    private String numImmAnnexe;

    private String dateDepartAnnexe;
    private enumTypeDoc typeDocAnnexe;
    private String numeroDebutAnnexe;
    private String numeroFinAnnexe;
     private boolean displayButtonAnnexe;
    private boolean displaydatefrgAnnexe;
    private boolean displayFinListfrgAnnexe;
    List<String> numsfinAnnexe=new ArrayList<>();

    public String getPrefixeAnnexe() {
        return prefixeAnnexe;
    }

    public void setPrefixeAnnexe(String prefixeAnnexe) {
        this.prefixeAnnexe = prefixeAnnexe;
    }

    public String getNomnavAnnexe() {
        return nomnavAnnexe;
    }

    public void setNomnavAnnexe(String nomnavAnnexe) {
        this.nomnavAnnexe = nomnavAnnexe;
    }

    public String getNumImmAnnexe() {
        return numImmAnnexe;
    }

    public void setNumImmAnnexe(String numImmAnnexe) {
        this.numImmAnnexe = numImmAnnexe;
    }

    public enumTypeDoc getTypeDocAnnexe() {
        return typeDocAnnexe;
    }

    public void setTypeDocAnnexe(enumTypeDoc typeDocAnnexe) {
        this.typeDocAnnexe = typeDocAnnexe;
    }

    public String getNumeroDebutAnnexe() {
        return numeroDebutAnnexe;
    }

    public void setNumeroDebutAnnexe(String numeroDebutAnnexe) {
        this.numeroDebutAnnexe = numeroDebutAnnexe;
    }

    public String getNumeroFinAnnexe() {
        return numeroFinAnnexe;
    }

    public void setNumeroFinAnnexe(String numeroFinAnnexe) {
        this.numeroFinAnnexe = numeroFinAnnexe;
    }

    public boolean isDisplayButtonAnnexe() {
        return displayButtonAnnexe;
    }

    public void setDisplayButtonAnnexe(boolean displayButtonAnnexe) {
        this.displayButtonAnnexe = displayButtonAnnexe;
    }

    public boolean isDisplaydatefrgAnnexe() {
        return displaydatefrgAnnexe;
    }

    public void setDisplaydatefrgAnnexe(boolean displaydatefrgAnnexe) {
        this.displaydatefrgAnnexe = displaydatefrgAnnexe;
    }

    public boolean isDisplayFinListfrgAnnexe() {
        return displayFinListfrgAnnexe;
    }

    public void setDisplayFinListfrgAnnexe(boolean displayFinListfrgAnnexe) {
        this.displayFinListfrgAnnexe = displayFinListfrgAnnexe;
    }

    public List<String> getNumsfinAnnexe() {
        return numsfinAnnexe;
    }

    public void setNumsfinAnnexe(List<String> numsfinAnnexe) {
        this.numsfinAnnexe = numsfinAnnexe;
    }

    public void setDateDepartAnnexe(String dateDepartAnnexe) {
        this.dateDepartAnnexe = dateDepartAnnexe;
    }

    public String getDateDepartAnnexe() {
        return dateDepartAnnexe;
    }
}