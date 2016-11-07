package com.gardecote.entities;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 05/11/2016.
 */
public class qJourPK implements Serializable {
    private Date dateJour;

    private String numImm;

    public qJourPK(Date dateJour, String numImm) {
        this.dateJour = dateJour;
        this.numImm = numImm;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public qJourPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qJourPK)) return false;

        qJourPK qJourPK = (qJourPK) o;

        if (!dateJour.equals(qJourPK.dateJour)) return false;
        return numImm.equals(qJourPK.numImm);

    }

    @Override
    public int hashCode() {
        int result = dateJour.hashCode();
        result = 31 * result + numImm.hashCode();
        return result;
    }
}
