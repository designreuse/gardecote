package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 16/10/2016.
 */

public class qQuantTraitPK implements  Serializable {
    private Date    depart;

    private String     numImm;



    public qQuantTraitPK(String numImm, Date depart) {
        this.numImm = numImm;
        this.depart = depart;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public qQuantTraitPK() {
        super();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qQuantTraitPK)) return false;

        qQuantTraitPK qDocPK = (qQuantTraitPK) o;

        if (!getDepart().equals(qDocPK.getDepart())) return false;
        return getNumImm().equals(qDocPK.getNumImm());

    }

    @Override
    public int hashCode() {
        int result = getDepart().hashCode();
        result = 31 * result + getNumImm().hashCode();
        return result;
    }
}
