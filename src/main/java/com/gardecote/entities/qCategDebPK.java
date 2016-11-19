package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 17/11/2016.
 */
public class qCategDebPK implements Serializable {
    @Id
    private String numimm;
    @Id
    private Date dateDepart;
    @Id
    private Integer idCat;

    public qCategDebPK(String numimm, Date dateDepart, Integer idCat) {
        this.numimm = numimm;
        this.dateDepart = dateDepart;
        this.idCat = idCat;
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

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCategDebPK)) return false;

        qCategDebPK that = (qCategDebPK) o;

        if (!getNumimm().equals(that.getNumimm())) return false;
        if (!getDateDepart().equals(that.getDateDepart())) return false;
        return getIdCat().equals(that.getIdCat());

    }

    @Override
    public int hashCode() {
        int result = getNumimm().hashCode();
        result = 31 * result + getDateDepart().hashCode();
        result = 31 * result + getIdCat().hashCode();
        return result;
    }

    public qCategDebPK() {
    }
}
