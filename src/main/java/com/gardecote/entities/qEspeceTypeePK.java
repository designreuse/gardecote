package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 27/10/2016.
 */
public class qEspeceTypeePK implements Serializable {


    private Integer qespeceId;

    private enumEspType enumesptype;


    private String idmodel;

    public qEspeceTypeePK() {
    }

    public qEspeceTypeePK(Integer qespeceId, enumEspType enumesptype, String idmodel) {
        this.qespeceId = qespeceId;
        this.enumesptype = enumesptype;
        this.idmodel = idmodel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceTypeePK)) return false;

        qEspeceTypeePK that = (qEspeceTypeePK) o;

        if (!qespeceId.equals(that.qespeceId)) return false;
        if (enumesptype != that.enumesptype) return false;
        return idmodel.equals(that.idmodel);

    }

    @Override
    public int hashCode() {
        int result = qespeceId.hashCode();
        result = 31 * result + enumesptype.hashCode();
        result = 31 * result + idmodel.hashCode();
        return result;
    }
}
