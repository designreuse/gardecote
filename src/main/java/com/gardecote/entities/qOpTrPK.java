package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 23/02/2017.
 */
public class qOpTrPK implements Serializable {

    private Integer IdOpTraitement;

    private String numeroPage;

    public qOpTrPK() {
    }

    public qOpTrPK(Integer idOpTraitement, String numeroPage) {
        IdOpTraitement = idOpTraitement;
        this.numeroPage = numeroPage;
    }

    public Integer getIdOpTraitement() {
        return IdOpTraitement;
    }

    public void setIdOpTraitement(Integer idOpTraitement) {
        IdOpTraitement = idOpTraitement;
    }

    public String getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(String numeroPage) {
        this.numeroPage = numeroPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qOpTrPK)) return false;

        qOpTrPK qOpTrPK = (qOpTrPK) o;

        if (!getIdOpTraitement().equals(qOpTrPK.getIdOpTraitement())) return false;
        return getNumeroPage().equals(qOpTrPK.getNumeroPage());

    }

    @Override
    public int hashCode() {
        int result = getIdOpTraitement().hashCode();
        result = 31 * result + getNumeroPage().hashCode();
        return result;
    }
}
