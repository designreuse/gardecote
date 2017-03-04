package com.gardecote.web;
import com.gardecote.entities.*;

import java.io.Serializable;
import java.util.List;
/**
 * Created by Dell on 01/12/2016.
 */
public class attributionCarnetForm  {
    private qNavireLegale navireSelected;
    private qUsine usineSelected;
    private qCarnet carnetSelected;
    private qLic licenceSelected;
    private List<qLic> licenceActives;
    private String message;
    private List<enumTypeDoc> lstTypeDoc;

    public qNavireLegale getNavireSelected() {
        return navireSelected;
    }

    public void setNavireSelected(qNavireLegale navireSelected) {
        this.navireSelected = navireSelected;
    }

    public List<enumTypeDoc> getLstTypeDoc() {
        return lstTypeDoc;
    }

    public void setLstTypeDoc(List<enumTypeDoc> lstTypeDoc) {
        this.lstTypeDoc = lstTypeDoc;
    }

    public qCarnet getCarnetSelected() {
        return carnetSelected;
    }

    public void setCarnetSelected(qCarnet carnetSelected) {
        this.carnetSelected = carnetSelected;
    }

    public qLic getLicenceSelected() {
        return licenceSelected;
    }

    public void setLicenceSelected(qLic licenceSelected) {
        this.licenceSelected = licenceSelected;
    }

    public List<qLic> getLicenceActives() {
        return licenceActives;
    }

    public void setLicenceActives(List<qLic> licenceActives) {
        this.licenceActives = licenceActives;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public qUsine getUsineSelected() {
        return usineSelected;
    }


    public void setUsineSelected(qUsine usineSelected) {
        this.usineSelected = usineSelected;
    }
}
