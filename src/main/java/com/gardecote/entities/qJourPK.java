package com.gardecote.entities;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 05/11/2016.
 */
public class qJourPK implements Serializable {
    private Date dateJour;

    private String numImm;
    private Integer       indexLigne   ;
    private String      numPage   ;

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

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public Integer getIndexLigne() {
        return indexLigne;
    }

    public void setIndexLigne(Integer indexLigne) {
        this.indexLigne = indexLigne;
    }

    public qJourPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qJourPK)) return false;

        qJourPK qJourPK = (qJourPK) o;

        if (!getDateJour().equals(qJourPK.getDateJour())) return false;
        if (!getNumImm().equals(qJourPK.getNumImm())) return false;
        if (!getIndexLigne().equals(qJourPK.getIndexLigne())) return false;
        return getNumPage().equals(qJourPK.getNumPage());

    }

    @Override
    public int hashCode() {
        int result = getDateJour().hashCode();
        result = 31 * result + getNumImm().hashCode();
        result = 31 * result + getIndexLigne().hashCode();
        result = 31 * result + getNumPage().hashCode();
        return result;
    }
}
