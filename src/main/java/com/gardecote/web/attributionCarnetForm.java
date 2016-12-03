package com.gardecote.web;

import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qNavire;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 01/12/2016.
 */
public class attributionCarnetForm  {
    private qNavire navireSelected;
    private qCarnet carnetSelected;
    private qLic licenceSelected;
    private List<qLic> licenceActives;
    private String message;

    public qNavire getNavireSelected() {
        return navireSelected;
    }

    public void setNavireSelected(qNavire navireSelected) {
        this.navireSelected = navireSelected;
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
}
