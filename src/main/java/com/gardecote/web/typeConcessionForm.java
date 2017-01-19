package com.gardecote.web;

import com.gardecote.entities.qTypeConcession;

import java.io.Serializable;

/**
 * Created by Dell on 12/01/2017.
 */
public class typeConcessionForm implements Serializable {

    private qTypeConcession typeConcession;
    private String typeOp;
    private String action;

    public qTypeConcession getTypeConcession() {
        return typeConcession;
    }

    public void setTypeConcession(qTypeConcession typeConcession) {
        this.typeConcession = typeConcession;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
