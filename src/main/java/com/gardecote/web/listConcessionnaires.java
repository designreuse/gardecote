package com.gardecote.web;

import com.gardecote.entities.qConcession;
import com.gardecote.entities.qConsignataire;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Created by Dell on 09/01/2017.
 */

public class listConcessionnaires implements Serializable {
    private Page<qConsignataire> lstConcessionnaires;
    private  int[] numPages;
    private int pageCourante;
    private String failedSuppression;
    private String RefMessage;

    private String concessionnairePKSelected=null;

    private int pageCount;

    private String searchConcessionnaire="";

    public Page<qConsignataire> getLstConcessionnaires() {
        return lstConcessionnaires;
    }

    public void setLstConcessionnaires(Page<qConsignataire> lstConcessionnaires) {
        this.lstConcessionnaires = lstConcessionnaires;
    }

    public int[] getNumPages() {
        return numPages;
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

    public String getConcessionnairePKSelected() {
        return concessionnairePKSelected;
    }

    public void setConcessionnairePKSelected(String concessionnairePKSelected) {
        this.concessionnairePKSelected = concessionnairePKSelected;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSearchConcessionnaire() {
        return searchConcessionnaire;
    }

    public void setSearchConcessionnaire(String searchConcessionnaire) {
        this.searchConcessionnaire = searchConcessionnaire;
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

    public listConcessionnaires() {
    }
}
