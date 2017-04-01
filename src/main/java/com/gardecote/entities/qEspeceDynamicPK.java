package com.gardecote.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 28/03/2017.
 */
public class qEspeceDynamicPK implements Serializable {

    private String     numeroPage;

    private enumTypeDoc     typeDoc;

    private Integer numOrdre;



    public Integer getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    public qEspeceDynamicPK() {
        super();
    }

    public String getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(String numeroPage) {
        this.numeroPage = numeroPage;
    }

    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public qEspeceDynamicPK(String numeroPage, enumTypeDoc typeDoc, Integer numOrdre) {
        this.numeroPage = numeroPage;
        this.typeDoc= typeDoc;
        this.numOrdre = numOrdre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qEspeceDynamicPK)) return false;

        qEspeceDynamicPK that = (qEspeceDynamicPK) o;

        if (!numeroPage.equals(that.numeroPage)) return false;
        if (typeDoc != that.typeDoc) return false;
        return getNumOrdre().equals(that.getNumOrdre());

    }

    @Override
    public int hashCode() {
        int result = numeroPage.hashCode();
        result = 31 * result + typeDoc.hashCode();
        result = 31 * result + getNumOrdre().hashCode();
        return result;
    }
}
