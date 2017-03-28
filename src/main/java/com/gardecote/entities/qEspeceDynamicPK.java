package com.gardecote.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 28/03/2017.
 */
public class qEspeceDynamicPK implements Serializable {

    private String numimm;

    private Date dateDepart;

    private Integer numOrdre;

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

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    public qEspeceDynamicPK() {
        super();
    }

    public qEspeceDynamicPK(String numimm, Date dateDepart, Integer numOrdre) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        this.numOrdre = numOrdre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceDynamicPK)) return false;

        qEspeceDynamicPK that = (qEspeceDynamicPK) o;

        if (!getNumimm().equals(that.getNumimm())) return false;
        if (!getDateDepart().equals(that.getDateDepart())) return false;
        return getNumOrdre().equals(that.getNumOrdre());

    }

    @Override
    public int hashCode() {
        int result = getNumimm().hashCode();
        result = 31 * result + getDateDepart().hashCode();
        result = 31 * result + getNumOrdre().hashCode();
        return result;
    }
}
