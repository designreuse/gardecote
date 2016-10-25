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
}
