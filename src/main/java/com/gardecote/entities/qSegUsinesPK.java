package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 20/12/2016.
 */
public class qSegUsinesPK  implements Serializable{
    @Id
    String refAgrement;
    @Id
    Date dateTraitement;
    @Id
    private enumSegPeche segPeche;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qSegUsinesPK)) return false;

        qSegUsinesPK that = (qSegUsinesPK) o;

        if (!getRefAgrement().equals(that.getRefAgrement())) return false;
        if (!getDateTraitement().equals(that.getDateTraitement())) return false;
        return getSegPeche() == that.getSegPeche();

    }

    @Override
    public int hashCode() {
        int result = getRefAgrement().hashCode();
        result = 31 * result + getDateTraitement().hashCode();
        result = 31 * result + getSegPeche().hashCode();
        return result;
    }

    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public enumSegPeche getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(enumSegPeche segPeche) {
        this.segPeche = segPeche;
    }
}
