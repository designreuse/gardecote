package com.gardecote.web;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Dell on 19/02/2017.
 */
public class searchAccueil {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date searchDateCapture;
    private String searchBat;
    private String searchUsine;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchDateTraitement;

    public searchAccueil() {
    }

    public Date getSearchDateCapture() {
        return searchDateCapture;
    }

    public void setSearchDateCapture(Date searchDateCapture) {
        this.searchDateCapture = searchDateCapture;
    }

    public String getSearchBat() {
        return searchBat;
    }

    public void setSearchBat(String searchBat) {
        this.searchBat = searchBat;
    }

    public String getSearchUsine() {
        return searchUsine;
    }

    public void setSearchUsine(String searchUsine) {
        this.searchUsine = searchUsine;
    }

    public Date getSearchDateTraitement() {
        return searchDateTraitement;
    }

    public void setSearchDateTraitement(Date searchDateTraitement) {
        this.searchDateTraitement = searchDateTraitement;
    }
}
