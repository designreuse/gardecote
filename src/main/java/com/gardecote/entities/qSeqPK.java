package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
public class qSeqPK implements Serializable {
    String  prefix;
    Long debut;

    public qSeqPK(String prefix, Long debut) {
        this.prefix = prefix;
        this.debut = debut;
    }

    public qSeqPK() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getDebut() {
        return debut;
    }

    public void setDebut(Long debut) {
        this.debut = debut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qSeqPK)) return false;

        qSeqPK qSeqPK = (qSeqPK) o;

        if (!prefix.equals(qSeqPK.prefix)) return false;
        return debut.equals(qSeqPK.debut);

    }

    @Override
    public int hashCode() {
        int result = prefix.hashCode();
        result = 31 * result + debut.hashCode();
        return result;
    }
}
