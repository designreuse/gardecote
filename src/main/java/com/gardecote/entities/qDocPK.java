package com.gardecote.entities;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 16/10/2016.
 */
@IdClass(qDocPK.class)
public class qDocPK implements Serializable {

    private qRegistreNavire     qnavire   ;

    private Date    depart       ;



    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof qDocPK)) return false;

        qDocPK qMarreePK = (qDocPK) o;

        if (qnavire != null ? !qnavire.equals(qMarreePK.qnavire) : qMarreePK.qnavire != null) return false;
        return !(depart != null ? !depart.equals(qMarreePK.depart) : qMarreePK.depart != null);

    }

    @Override
    public int hashCode() {
        int result = qnavire != null ? qnavire.hashCode() : 0;
        result = 31 * result + (depart != null ? depart.hashCode() : 0);
        return result;
    }

    public qDocPK(qRegistreNavire refBase, Date depart) {
        this.qnavire = refBase;
        this.depart = depart;
    }

    public qDocPK() {
        super();
    }
}
