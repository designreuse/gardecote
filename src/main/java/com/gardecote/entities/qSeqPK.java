package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Dell on 25/10/2016.
 */
public class qSeqPK implements Serializable {

    String debut;
    String fin;

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public qSeqPK() {
    }

    public qSeqPK(String debut, String fin) {
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qSeqPK)) return false;

        qSeqPK qSeqPK = (qSeqPK) o;

        if (!debut.equals(qSeqPK.debut)) return false;
        return fin.equals(qSeqPK.fin);

    }

    @Override
    public int hashCode() {
        int result = debut.hashCode();
        result = 31 * result + fin.hashCode();
        return result;
    }
}
