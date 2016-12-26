package com.gardecote.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 23/12/2016.
 */
public class qPageCarnetPK implements Serializable{
    @Id
    @Column(name="numPage", nullable=false, length=50)
    private String     numeroPage;
    @Id
    @Column(name="typeDoc", nullable=false, length=50)
    private enumTypeDoc     typeDoc;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qPageCarnetPK)) return false;

        qPageCarnetPK that = (qPageCarnetPK) o;

        if (!getNumeroPage().equals(that.getNumeroPage())) return false;
        return getTypeDoc().equals(that.getTypeDoc());

    }

    public qPageCarnetPK() {
    }

    @Override
    public int hashCode() {
        int result = getNumeroPage().hashCode();
        result = 31 * result + getTypeDoc().hashCode();
        return result;
    }

    public qPageCarnetPK(String numeroPage, enumTypeDoc typeDoc) {
        this.numeroPage = numeroPage;
        this.typeDoc = typeDoc;
    }
}
