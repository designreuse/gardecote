package com.gardecote.entities;

/**
 * Created by Dell on 25/10/2016.
 */

public class qEspeceTypee {
    private qEspece qespece;
    private enumEspType enumesptype;
    private qTypeConcession qtypeconcession;
    private qModelJP modelesp;

    public qEspeceTypee(qEspece qespece, enumEspType enumesptype, qTypeConcession qtypeconcession) {
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

    public qModelJP getModelesp() {
        return modelesp;
    }

    public void setModelesp(qModelJP modelesp) {
        this.modelesp = modelesp;
    }
}
