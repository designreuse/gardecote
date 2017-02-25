package com.gardecote.models;

import javax.persistence.*;
import java.io.Serializable;

public class qTypeNavModel  {

    private String idqTypeNav;
    private String descr;

    public qTypeNavModel(String idqTypeNav, String descr) {
        this.idqTypeNav = idqTypeNav;
        this.descr = descr;
    }

    public qTypeNavModel() {
    }

    public String getIdqTypeNav() {
        return idqTypeNav;
    }

    public void setIdqTypeNav(String idqTypeNav) {
        this.idqTypeNav = idqTypeNav;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
