package com.gardecote.entities;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 05/11/2016.
 */
public class qEnginPechePK implements Serializable {


    @Id
    private String numimm;
    @Id
    private Date dateDepart;
    @Id
    private enumEngin EnginMar;
    @Id
    private enumEnginDeb EnginDeb;

    public qEnginPechePK() {
    }

    public qEnginPechePK(String numimm, Date dateDepart, enumEngin enginMar, enumEnginDeb enginDeb) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        EnginMar = enginMar;
        EnginDeb = enginDeb;
    }

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
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
        if (!(o instanceof qEnginPechePK)) return false;

        qEnginPechePK that = (qEnginPechePK) o;

        if (!getNumimm().equals(that.getNumimm())) return false;
        if (!getDateDepart().equals(that.getDateDepart())) return false;
        if (getEnginMar() != that.getEnginMar()) return false;
        return getEnginDeb() == that.getEnginDeb();

    }

    @Override
    public int hashCode() {
        int result = getNumimm().hashCode();
        result = 31 * result + getDateDepart().hashCode();
        result = 31 * result + (getEnginMar() != null ? getEnginMar().hashCode() : 0);
        result = 31 * result + (getEnginDeb() != null ? getEnginDeb().hashCode() : 0);
        return result;
    }
}
