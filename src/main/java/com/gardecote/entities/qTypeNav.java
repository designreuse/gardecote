package com.gardecote.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
/**
 * Created by Dell on 09/10/2016.
 */
@Entity
public class qTypeNav implements Serializable {
    @Id
    private String idqTypeNav;
    private String descr;

    public qTypeNav(String idqTypeNav, String descr) {
        this.idqTypeNav = idqTypeNav;
        this.descr = descr;
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
