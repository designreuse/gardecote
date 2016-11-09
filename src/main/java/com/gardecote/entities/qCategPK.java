package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 08/11/2016.
 */
public class qCategPK  implements Serializable{

    private Integer idtypeConcession;

    public qCategPK(Integer idtypeConcession) {
        this.idtypeConcession = idtypeConcession;
    }

    public qCategPK() {
    }

    public Integer getIdtypeConcession() {
        return idtypeConcession;
    }

    public void setIdtypeConcession(Integer idtypeConcession) {
        this.idtypeConcession = idtypeConcession;
    }
}
