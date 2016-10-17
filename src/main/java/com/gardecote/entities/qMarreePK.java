package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 16/10/2016.
 */
@IdClass(qMarreePK.class)
public class qMarreePK implements Serializable {

    private String     refBase   ;

    private Date    depart       ;

    public String getRefBase() {
        return refBase;
    }

    public void setRefBase(String refBase) {
        this.refBase = refBase;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qMarreePK)) return false;

        qMarreePK qMarreePK = (qMarreePK) o;

        if (!refBase.equals(qMarreePK.refBase)) return false;
        return depart.equals(qMarreePK.depart);

    }

    @Override
    public int hashCode() {
        int result = refBase.hashCode();
        result = 31 * result + depart.hashCode();
        return result;
    }

    public qMarreePK(String refBase, Date depart) {
        this.refBase = refBase;
        this.depart = depart;
    }

    public qMarreePK() {
        super();
    }
}
