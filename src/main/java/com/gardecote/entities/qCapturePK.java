package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 06/11/2016.
 */

public class qCapturePK implements Serializable{

    private Date           datedepart;


    private    String      nummimm;

    private    Date        dateJour;

    private Integer          indexLigne;

    private String         numPage ;

    private    String      idespece;

    private    enumEspType esptype;

    public qCapturePK(Date datedepart, String nummimm, Date dateJour, String idespece, enumEspType esptype) {
        this.datedepart = datedepart;
        this.nummimm = nummimm;
        this.dateJour = dateJour;
        this.idespece = idespece;
        this.esptype = esptype;
    }

    public qCapturePK() {
    }

    public Integer getIndexLigne() {
        return indexLigne;
    }

    public void setIndexLigne(Integer indexLigne) {
        this.indexLigne = indexLigne;
    }

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }

    public Date getDatedepart() {
        return datedepart;
    }


    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

    public String getNummimm() {
        return nummimm;
    }

    public void setNummimm(String nummimm) {
        this.nummimm = nummimm;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public String getIdespece() {
        return idespece;
    }

    public void setIdespece(String idespece) {
        this.idespece = idespece;
    }

    public enumEspType getEsptype() {
        return esptype;
    }

    public void setEsptype(enumEspType esptype) {
        this.esptype = esptype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCapturePK)) return false;

        qCapturePK that = (qCapturePK) o;

        if (!getDatedepart().equals(that.getDatedepart())) return false;
        if (!getNummimm().equals(that.getNummimm())) return false;
        if (!getDateJour().equals(that.getDateJour())) return false;
        if (!getIndexLigne().equals(that.getIndexLigne())) return false;
        if (!getNumPage().equals(that.getNumPage())) return false;
        if (!getIdespece().equals(that.getIdespece())) return false;
        return getEsptype() == that.getEsptype();

    }

    @Override
    public int hashCode() {
        int result = getDatedepart().hashCode();
        result = 31 * result + getNummimm().hashCode();
        result = 31 * result + getDateJour().hashCode();
        result = 31 * result + getIndexLigne().hashCode();
        result = 31 * result + getNumPage().hashCode();
        result = 31 * result + getIdespece().hashCode();
        result = 31 * result + getEsptype().hashCode();
        return result;
    }
}
