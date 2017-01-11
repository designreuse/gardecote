package com.gardecote.web;

import com.gardecote.entities.qConcession;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/01/2017.
 */
public class listConcessions implements Serializable{
    private Page<qConcession> lstConcession;
    private  int[] numPages;
    private int pageCourante;
    private String failedSuppression;
    private String RefMessage;

    private String concessionPKSelected=null;

    private int pageCount;

    private String searchConcession="";
    public listConcessions(Page<qConcession> lstConcession, int[] numPages, int pageCourante) {
        this.lstConcession = lstConcession;
        this.numPages = numPages;
        this.pageCourante = pageCourante;
    }

    public Page<qConcession> getLstConcession() {
        return lstConcession;
    }

    public listConcessions() {
    }

    public String getFailedSuppression() {
        return failedSuppression;
    }

    public void setFailedSuppression(String failedSuppression) {
        this.failedSuppression = failedSuppression;
    }

    public String getRefMessage() {
        return RefMessage;
    }

    public void setRefMessage(String refMessage) {
        RefMessage = refMessage;
    }

    public String getConcessionPKSelected() {
        return concessionPKSelected;
    }

    public void setConcessionPKSelected(String concessionPKSelected) {
        this.concessionPKSelected = concessionPKSelected;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSearchConcession() {
        return searchConcession;
    }

    public void setSearchConcession(String searchConcession) {
        this.searchConcession = searchConcession;
    }

    public void setLstConcession(Page<qConcession> lstConcession) {
        this.lstConcession = lstConcession;
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
}
