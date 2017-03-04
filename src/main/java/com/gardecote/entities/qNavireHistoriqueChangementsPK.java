package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 04/03/2017.
 */
public class qNavireHistoriqueChangementsPK implements Serializable {

    private String numimm;

    private enumHistoriqueNavire typeHystaurique;

    private String numLic;

    public String getNumimm() {
        return numimm;
    }

    public void setNumimm(String numimm) {
        this.numimm = numimm;
    }

    public qNavireHistoriqueChangementsPK() {
    }

    public enumHistoriqueNavire getTypeHystaurique() {
        return typeHystaurique;
    }

    public void setTypeHystaurique(enumHistoriqueNavire typeHystaurique) {
        this.typeHystaurique = typeHystaurique;
    }

    public String getNumLic() {
        return numLic;
    }

    public void setNumLic(String numLic) {
        this.numLic = numLic;
    }

    public qNavireHistoriqueChangementsPK(String numimm, enumHistoriqueNavire typeHystaurique, String numLic) {
        this.numimm = numimm;
        this.typeHystaurique = typeHystaurique;
        this.numLic = numLic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qNavireHistoriqueChangementsPK)) return false;

        qNavireHistoriqueChangementsPK that = (qNavireHistoriqueChangementsPK) o;

        if (!getNumimm().equals(that.getNumimm())) return false;
        if (getTypeHystaurique() != that.getTypeHystaurique()) return false;
        return getNumLic().equals(that.getNumLic());

    }

    @Override
    public int hashCode() {
        int result = getNumimm().hashCode();
        result = 31 * result + getTypeHystaurique().hashCode();
        result = 31 * result + getNumLic().hashCode();
        return result;
    }
}
