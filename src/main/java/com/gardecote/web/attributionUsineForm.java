package com.gardecote.web;

import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qLic;

import com.gardecote.entities.qNavireLegale;
import com.gardecote.entities.qUsine;

import java.util.List;

/**
 * Created by Dell on 28/12/2016.
 */

public class attributionUsineForm {

    private qUsine usineSelected;
    private qNavireLegale navireSelected;
    private qCarnet carnetSelected;

    private String message;

    public qUsine getUsineSelected() {
        return usineSelected;
    }

    public void setUsineSelected(qUsine usineSelected) {
        this.usineSelected = usineSelected;
    }

    public  qNavireLegale  getNavireSelected() {
        return navireSelected;
    }

    public void setNavireSelected( qNavireLegale  navireSelected) {
        this.navireSelected = navireSelected;
    }

    public qCarnet getCarnetSelected() {
        return carnetSelected;
    }

    public void setCarnetSelected(qCarnet carnetSelected) {
        this.carnetSelected = carnetSelected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
