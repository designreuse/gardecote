package com.gardecote.reports;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 19/03/2017.
 */
public class Data {

    private Date depart;
    private List<Object> pages;

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Data() {
    }

    public List<Object> getPages() {
        return pages;
    }

    public void setPages(List<Object> pages) {
        this.pages = pages;
    }

    public Data(Date depart, List<Object> pages) {
        this.depart = depart;
        this.pages = pages;
    }
}
