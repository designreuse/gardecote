package com.gardecote.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 06/11/2016.
 */

public class qCapturePK implements Serializable{
    @Id
    private Date           datedepart;
    @Id
    private    String      nummimm;
    @Id
    private    Date        dateJour;
    @Id
    private    String      idespece;
    @Id
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

    public Date getDatedepart() {
        return datedepart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qCapturePK)) return false;

        qCapturePK that = (qCapturePK) o;

        if (!datedepart.equals(that.datedepart)) return false;
        if (!nummimm.equals(that.nummimm)) return false;
        if (!dateJour.equals(that.dateJour)) return false;
        if (!idespece.equals(that.idespece)) return false;
        return esptype == that.esptype;

    }

    @Override
    public int hashCode() {
        int result = datedepart.hashCode();
        result = 31 * result + nummimm.hashCode();
        result = 31 * result + dateJour.hashCode();
        result = 31 * result + idespece.hashCode();
        result = 31 * result + esptype.hashCode();
        return result;
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
}
