package com.gardecote.entities;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;
/**
 * Created by Dell on 16/10/2016.
 */
@IdClass(qDocPK.class)
public class qDocPK implements Serializable {

    private String     qnavire   ;

    private Date    depart       ;



    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public String getQnavire() {
        return qnavire;
    }

    public void setQnavire(String qnavire) {
        this.qnavire = qnavire;
    }

    public qDocPK(String refBase, Date depart) {
        this.qnavire = refBase;
        this.depart = depart;
    }

    public qDocPK() {
        super();
    }

}
