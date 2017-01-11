package com.gardecote.entities;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 05/11/2016.
 */
public class qJourPKAnnexe implements Serializable {
    private Date dateJour;

    private String numImm;
    private Integer       indexLigne   ;

    public qJourPKAnnexe(Date dateJour, String numImm, Integer indexLigne) {
        this.dateJour = dateJour;
        this.numImm = numImm;
        this.indexLigne = indexLigne;
    }

    public Integer getIndexLigne() {
        return indexLigne;
    }

    public void setIndexLigne(Integer indexLigne) {
        this.indexLigne = indexLigne;
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

    public qJourPKAnnexe() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qJourPKAnnexe)) return false;

        qJourPKAnnexe qJourPK = (qJourPKAnnexe) o;

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
