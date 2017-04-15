package com.gardecote.web;

import com.gardecote.entities.enumTypeDoc;
import com.gardecote.entities.qDoc;
import org.springframework.data.domain.Page;

/**
 * Created by Dell on 08/04/2017.
 */
public class frmSearchResultCaptures {
    private Page<Object> lstJours;
    private Integer pageCount;
    private int[]   numPages;
    private Integer pageCourante;


    public frmSearchResultCaptures() {
    }

    public Page<Object> getLstJours() {
        return lstJours;
    }

    public void setLstJours(Page<Object> lstJours) {
        this.lstJours = lstJours;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public int[] getNumPages() {
        return numPages;
    }

    public void setNumPages(int[] numPages) {
        this.numPages = numPages;
    }

    public Integer getPageCourante() {
        return pageCourante;
    }

    public void setPageCourante(Integer pageCourante) {
        this.pageCourante = pageCourante;
    }
}
