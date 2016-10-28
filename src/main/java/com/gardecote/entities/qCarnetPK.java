package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */

public class qCarnetPK implements Serializable {

       private enumPrefix     prefixNumerotation    ;

       private Long       numeroDebutPage    ;

       public qCarnetPK() {
        super();
    }

    public qCarnetPK(enumPrefix prefixNumerotation, Long numeroDebutPage) {
        this.prefixNumerotation = prefixNumerotation;
        this.numeroDebutPage = numeroDebutPage;
    }

    public enumPrefix getPrefixNumerotation() {
        return prefixNumerotation;
    }

    public void setPrefixNumerotation(enumPrefix prefixNumerotation) {
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

        if (prefixNumerotation != qCarnetPK.prefixNumerotation) return false;
        return numeroDebutPage.equals(qCarnetPK.numeroDebutPage);

    }

    @Override
    public int hashCode() {
        int result = prefixNumerotation.hashCode();
        result = 31 * result + numeroDebutPage.hashCode();
        return result;
    }
}
