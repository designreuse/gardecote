package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 03/03/2017.
 */
public class qEnginAuthoriseePK implements Serializable{

    private String numlic;

    private enumEngin EnginMar;

    private enumEnginDeb EnginDeb;

    public qEnginAuthoriseePK(String numlic, enumEngin enginMar, enumEnginDeb enginDeb) {
        this.numlic = numlic;
        EnginMar = enginMar;
        EnginDeb = enginDeb;
    }

    public qEnginAuthoriseePK() {
    }

    public String getNumlic() {
        return numlic;
    }

    public void setNumlic(String numlic) {
        this.numlic = numlic;
    }

    public enumEngin getEnginMar() {
        return EnginMar;
    }

    public void setEnginMar(enumEngin enginMar) {
        EnginMar = enginMar;
    }

    public enumEnginDeb getEnginDeb() {
        return EnginDeb;
    }

    public void setEnginDeb(enumEnginDeb enginDeb) {
        EnginDeb = enginDeb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEnginAuthoriseePK)) return false;

        qEnginAuthoriseePK that = (qEnginAuthoriseePK) o;

        if (!getNumlic().equals(that.getNumlic())) return false;
        if (getEnginMar() != that.getEnginMar()) return false;
        return getEnginDeb() == that.getEnginDeb();

    }

    @Override
    public int hashCode() {
        int result = getNumlic().hashCode();
        result = 31 * result + getEnginMar().hashCode();
        result = 31 * result + getEnginDeb().hashCode();
        return result;
    }
}
