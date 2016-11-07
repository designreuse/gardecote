package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 27/10/2016.
 */
public class qEspeceTypeePK implements Serializable {


    private String qespeceId;

    private enumEspType enumesptype;




    public qEspeceTypeePK() {
    }

    public qEspeceTypeePK(String qespeceId, enumEspType enumesptype) {
        this.qespeceId = qespeceId;
        this.enumesptype = enumesptype;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceTypeePK)) return false;

        qEspeceTypeePK that = (qEspeceTypeePK) o;

        if (!qespeceId.equals(that.qespeceId)) return false;
        return enumesptype == that.enumesptype;

    }

    @Override
    public int hashCode() {
        int result = qespeceId.hashCode();
        result = 31 * result + enumesptype.hashCode();
        return result;
    }
}
