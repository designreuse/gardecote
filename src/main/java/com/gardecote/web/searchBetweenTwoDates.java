package com.gardecote.web;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Dell on 04/04/2017.
 */
public class searchBetweenTwoDates {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date searchDateCapture1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date searchDateCapture2;

    private String searchBat;

    public searchBetweenTwoDates() {
    }

    public searchBetweenTwoDates(Date searchDateCapture1, Date searchDateCapture2, String searchBat) {
        this.searchDateCapture1 = searchDateCapture1;
        this.searchDateCapture2 = searchDateCapture2;
        this.searchBat = searchBat;
    }

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

    public String getSearchBat() {
        return searchBat;
    }

    public void setSearchBat(String searchBat) {
        this.searchBat = searchBat;
    }
}
