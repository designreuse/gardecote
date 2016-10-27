package com.gardecote.entities;

import java.io.Serializable;

/**
 * Created by Dell on 27/10/2016.
 */
public class qEspeceTypeePK implements Serializable {

    private qEspece qespece;

    private enumEspType enumesptype;

    private qTypeConcession qtypeconcession;

    public qEspeceTypeePK(qEspece qespece, enumEspType enumesptype, qTypeConcession qtypeconcession) {
        this.qespece = qespece;
        this.enumesptype = enumesptype;
        this.qtypeconcession = qtypeconcession;
    }

    public qEspece getQespece() {
        return qespece;
    }

    public void setQespece(qEspece qespece) {
        this.qespece = qespece;
    }

    public enumEspType getEnumesptype() {
        return enumesptype;
    }

    public void setEnumesptype(enumEspType enumesptype) {
        this.enumesptype = enumesptype;
    }

    public qTypeConcession getQtypeconcession() {
        return qtypeconcession;
    }

    public void setQtypeconcession(qTypeConcession qtypeconcession) {
        this.qtypeconcession = qtypeconcession;
    }
}
