package com.gardecote.entities;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 16/10/2016.
 */

public class qDocPK implements  Serializable {
    private Date    depart       ;

    private String     numImm   ;



    public qDocPK(String numImm, Date depart) {
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

    public qDocPK() {
        super();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qDocPK)) return false;

        qDocPK qDocPK = (qDocPK) o;

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
