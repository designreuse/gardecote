package com.gardecote.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lehbib on 14/05/2017.
 */
public class resultatCapturesCl implements Serializable {
    private Date dateCapture;
    private String navire;
    private Object resultatCapEsp;

    public Date getDateCapture() {
        return dateCapture;
    }

    public resultatCapturesCl() {
    }

    public void setDateCapture(Date dateCapture) {
        this.dateCapture = dateCapture;
    }

    public String getNavire() {
        return navire;
    }

    public void setNavire(String navire) {
        this.navire = navire;
    }

    public Object getResultatCapEsp() {
        return resultatCapEsp;
    }

    public void setResultatCapEsp(Object resultatCapEsp) {
        this.resultatCapEsp = resultatCapEsp;
    }

    public resultatCapturesCl(Date dateCapture, String navire, Object resultatCapEsp) {
        this.dateCapture = dateCapture;
        this.navire = navire;
        this.resultatCapEsp = resultatCapEsp;
    }
}
