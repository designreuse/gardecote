package com.gardecote.web;

import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qNavire;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by Dell on 27/11/2016.
 */
public class lstCarnetsAchoisirForm  {

    private Page<qCarnet> lstCarnets=null;
    private String failedAnnulation;
    private String RefMessage;

    private String carnetPKSelected=null;

    private int pageCount;
    private int[] numPages;
    private int pageCourante;
    private String searchCarnet="";

    public String getFailedAnnulation() {
        return failedAnnulation;
    }

    public void setFailedAnnulation(String failedAnnulation) {
        this.failedAnnulation = failedAnnulation;
    }

    public lstCarnetsAchoisirForm() {
    }

    public Page<qCarnet> getLstCarnets() {
        return lstCarnets;
    }

    public void setLstCarnets(Page<qCarnet> lstCarnets) {
        this.lstCarnets = lstCarnets;
    }

    public String getRefMessage() {
        return RefMessage;
    }

    public void setRefMessage(String refMessage) {
        RefMessage = refMessage;
    }

    public String getCarnetPKSelected() {
        return carnetPKSelected;
    }

    public void setCarnetPKSelected(String carnetPKSelected) {
        this.carnetPKSelected = carnetPKSelected;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int[] getNumPages() {
        return numPages;
    }

    public void setNumPages(int[] numPages) {
        this.numPages = numPages;
    }

    public int getPageCourante() {
        return pageCourante;
    }

    public void setPageCourante(int pageCourante) {
        this.pageCourante = pageCourante;
    }

    public String getSearchCarnet() {
        return searchCarnet;
    }

    public void setSearchCarnet(String searchCarnet) {
        this.searchCarnet = searchCarnet;
    }
}
