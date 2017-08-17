package com.gardecote.web;

import com.gardecote.entities.qPrefix;
import com.gardecote.entities.qTaskProgressBar;
import com.gardecote.entities.qTypeConcession;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 05/04/2017.
 */
public class searchBetweenTwoDatesByConcession {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchDateCapture1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchDateCapture2;

    private  Boolean exclureDetails;
    private String nom;
    private qTaskProgressBar progressBar;

    private List<choixTypeConcession> types;

    public Date getSearchDateCapture1() {
        return searchDateCapture1;
    }

    public void setSearchDateCapture1(Date searchDateCapture1) {
        this.searchDateCapture1 = searchDateCapture1;
    }

    public Date getSearchDateCapture2() {
        return searchDateCapture2;
    }

    public void setSearchDateCapture2(Date searchDateCapture2) {
        this.searchDateCapture2 = searchDateCapture2;
    }

    public List<choixTypeConcession> getTypes() {
        return types;
    }

    public void setTypes(List<choixTypeConcession> types) {
        this.types = types;
    }

    public qTaskProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(qTaskProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public searchBetweenTwoDatesByConcession() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getExclureDetails() {
        return exclureDetails;
    }

    public void setExclureDetails(Boolean exclureDetails) {
        this.exclureDetails = exclureDetails;
    }
}
