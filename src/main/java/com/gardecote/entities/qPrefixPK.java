package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 22/12/2016.
 */

public class qPrefixPK implements Serializable{

    private String prefix;

    private enumTypeDoc typeDoc;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public qPrefixPK() {
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
        if (!(o instanceof qPrefixPK)) return false;

        qPrefixPK qPrefixPK = (qPrefixPK) o;

        if (!getPrefix().equals(qPrefixPK.getPrefix())) return false;
        return getTypeDoc() == qPrefixPK.getTypeDoc();

    }

    @Override
    public int hashCode() {
        int result = getPrefix().hashCode();
        result = 31 * result + getTypeDoc().hashCode();
        return result;
    }

    public qPrefixPK(String prefix, enumTypeDoc typeDoc) {
        this.prefix = prefix;
        this.typeDoc = typeDoc;
    }

}
