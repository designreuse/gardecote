package com.gardecote.web;

import com.gardecote.entities.qLic;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 23/11/2016.
 */
public class lstLicForm implements Serializable{
   private Page<qLic> licences;
    private int pageCount;
    private  int[] numPages;
    private int pageCourante;
    private String searchNomNav;

    public lstLicForm(Page<qLic> licences) {
        this.licences = licences;
    }

    public lstLicForm() {

    }

    public String getSearchNomNav() {
        return searchNomNav;
    }

    public void setSearchNomNav(String searchNomNav) {
        this.searchNomNav = searchNomNav;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageCourante() {
        return pageCourante;
    }

    public void setPageCourante(int pageCourante) {
        this.pageCourante = pageCourante;
    }

    public int[] getNumPages() {

        return numPages;
    }

    public void setNumPages(int[] numPages) {
        this.numPages = numPages;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Page<qLic> getLicences() {
        return licences;
    }

    public void setLicences(Page<qLic> licences) {
        this.licences = licences;
    }
}
