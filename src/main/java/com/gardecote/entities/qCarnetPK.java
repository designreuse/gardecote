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

       private enumTypeDoc  typeDoc;

       public qCarnetPK() {
        super();
    }

    public qCarnetPK(String prefixNumerotation, Long numeroDebutPage, enumTypeDoc typeDoc) {
        this.prefixNumerotation = prefixNumerotation;
        this.numeroDebutPage = numeroDebutPage;
        this.typeDoc = typeDoc;
    }

    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
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

        if (!getPrefixNumerotation().equals(qCarnetPK.getPrefixNumerotation())) return false;
        if (!getNumeroDebutPage().equals(qCarnetPK.getNumeroDebutPage())) return false;
        return getTypeDoc() == qCarnetPK.getTypeDoc();

    }

    @Override
    public int hashCode() {
        int result = getPrefixNumerotation().hashCode();
        result = 31 * result + getNumeroDebutPage().hashCode();
        result = 31 * result + getTypeDoc().hashCode();
        return result;
    }
}
