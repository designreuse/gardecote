package com.gardecote.entities;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by Dell on 16/11/2016.
 */

public class qEnginsLicencePK implements Serializable {
    @Id
    private enumEnginDeb EnginDeb;

    @Id
    private enumEngin EnginMar;

    @Id
    private Integer maillage;

    public qEnginsLicencePK() {
    }

    public qEnginsLicencePK(enumEnginDeb enginDeb, enumEngin enginMar, Integer maillage) {
        EnginDeb = enginDeb;
        EnginMar = enginMar;
        this.maillage = maillage;
    }

    public enumEnginDeb getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(enumEnginDeb enginDeb) {
        EnginDeb = enginDeb;
    }

    public enumEngin getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(enumEngin enginMar) {
        EnginMar = enginMar;
    }

    public Integer getMaillage() {
        return maillage;
    }

    public void setMaillage(Integer maillage) {
        this.maillage = maillage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEnginsLicencePK)) return false;

        qEnginsLicencePK that = (qEnginsLicencePK) o;

        if (getEnginDeb() != that.getEnginDeb()) return false;
        if (getEnginMar() != that.getEnginMar()) return false;
        return getMaillage().equals(that.getMaillage());

    }

    @Override
    public int hashCode() {
        int result = getEnginDeb().hashCode();
        result = 31 * result + getEnginMar().hashCode();
        result = 31 * result + getMaillage().hashCode();
        return result;
    }
}
