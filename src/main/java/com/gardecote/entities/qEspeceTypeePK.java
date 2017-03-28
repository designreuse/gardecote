package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 27/10/2016.
 */
public class qEspeceTypeePK implements Serializable {


    private String qespeceId;

    private enumEspType enumesptype;
    private Integer numOrdre;
    private String prefix;
    private enumTypeEspTypee typeesptypee;


    public qEspeceTypeePK() {
    }

    public qEspeceTypeePK(String qespeceId, enumEspType enumesptype, Integer numOrdre,String prefix,enumTypeEspTypee typeesptypee) {
        this.qespeceId = qespeceId;
        this.enumesptype = enumesptype;
        this.numOrdre = numOrdre;
        this.prefix=prefix;
        this.typeesptypee=typeesptypee;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public enumTypeEspTypee getTypeesptypee() {
        return typeesptypee;
    }

    public void setTypeesptypee(enumTypeEspTypee typeesptypee) {
        this.typeesptypee = typeesptypee;
    }

    public String getQespeceId() {
        return qespeceId;
    }

    public void setQespeceId(String qespeceId) {
        this.qespeceId = qespeceId;
    }

    public enumEspType getEnumesptype() {
        return enumesptype;
    }

    public void setEnumesptype(enumEspType enumesptype) {
        this.enumesptype = enumesptype;
    }

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceTypeePK)) return false;

        qEspeceTypeePK that = (qEspeceTypeePK) o;

        if (!getQespeceId().equals(that.getQespeceId())) return false;
        if (getEnumesptype() != that.getEnumesptype()) return false;
        if (!getNumOrdre().equals(that.getNumOrdre())) return false;
        if (!getPrefix().equals(that.getPrefix())) return false;
        return getTypeesptypee() == that.getTypeesptypee();

    }

    @Override
    public int hashCode() {
        int result = getQespeceId().hashCode();
        result = 31 * result + getEnumesptype().hashCode();
        result = 31 * result + getNumOrdre().hashCode();
        result = 31 * result + getPrefix().hashCode();
        result = 31 * result + getTypeesptypee().hashCode();
        return result;
    }
}
