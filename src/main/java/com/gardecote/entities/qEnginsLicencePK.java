package com.gardecote.entities;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by Dell on 16/11/2016.
 */

public class qEnginsLicencePK implements Serializable {
    private enumEnginDeb EnginDeb;
    private enumEngin EnginMar;

    public qEnginsLicencePK() {
    }

    public qEnginsLicencePK(enumEnginDeb enginDeb, enumEngin enginMar) {
        EnginDeb = enginDeb;
        EnginMar = enginMar;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEnginsLicencePK)) return false;

        qEnginsLicencePK that = (qEnginsLicencePK) o;

        if (getEnginDeb() != that.getEnginDeb()) return false;
        return getEnginMar() == that.getEnginMar();

    }

    @Override
    public int hashCode() {
        int result = getEnginDeb().hashCode();
        result = 31 * result + getEnginMar().hashCode();
        return result;
    }
}
