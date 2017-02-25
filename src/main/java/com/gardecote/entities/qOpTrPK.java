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
}
