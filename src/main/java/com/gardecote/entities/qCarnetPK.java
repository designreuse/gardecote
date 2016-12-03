package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */

public class qCarnetPK implements Serializable {

       private String     prefixNumerotation    ;

       private Long       numeroDebutPage    ;

       public qCarnetPK() {
        super();
    }

    public qCarnetPK(String prefixNumerotation, Long numeroDebutPage) {
        this.prefixNumerotation = prefixNumerotation;
        this.numeroDebutPage = numeroDebutPage;
    }

    public String getPrefixNumerotation() {
        return prefixNumerotation;
    }

    public void setPrefixNumerotation(String prefixNumerotation) {
        this.prefixNumerotation = prefixNumerotation;
    }

    public Long getNumeroDebutPage() {
        return numeroDebutPage;
    }

    public void setNumeroDebutPage(Long numeroDebutPage) {
        this.numeroDebutPage = numeroDebutPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCarnetPK)) return false;

        qCarnetPK qCarnetPK = (qCarnetPK) o;

        if (getPrefixNumerotation() != null ? !getPrefixNumerotation().equals(qCarnetPK.getPrefixNumerotation()) : qCarnetPK.getPrefixNumerotation() != null)
            return false;
        return !(getNumeroDebutPage() != null ? !getNumeroDebutPage().equals(qCarnetPK.getNumeroDebutPage()) : qCarnetPK.getNumeroDebutPage() != null);

    }

    @Override
    public int hashCode() {
        int result = getPrefixNumerotation() != null ? getPrefixNumerotation().hashCode() : 0;
        result = 31 * result + (getNumeroDebutPage() != null ? getNumeroDebutPage().hashCode() : 0);
        return result;
    }
}
