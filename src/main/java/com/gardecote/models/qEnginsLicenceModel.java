package com.gardecote.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gardecote.entities.enumEngin;
import com.gardecote.entities.enumEnginDeb;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qEnginsLicencePK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class qEnginsLicenceModel implements Serializable {

    private String EnginDeb;

    private String EnginMar;

    private Integer maillage;

    public qEnginsLicenceModel() {
    }

    public String getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(String enginDeb) {
        EnginDeb = enginDeb;
    }

    public String getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(String enginMar) {
        EnginMar = enginMar;
    }

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }
}
