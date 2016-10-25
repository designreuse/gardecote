package com.gardecote.entities;

import javax.persistence.Entity;

/**
 * Created by Dell on 09/10/2016.
 */

public class qTypeNav {
    private String idqTypeNav;
    private String descr;

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
