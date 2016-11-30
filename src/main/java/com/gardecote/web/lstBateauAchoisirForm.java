package com.gardecote.web;

import com.gardecote.entities.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 27/11/2016.
 */
public class lstBateauAchoisirForm implements Serializable {

    private Page<qNavire> lstBat=null;

    @Valid
    private qLic licence;

    private String RefMessage;

    private String numSelected=null;
    private qCategRessource selectedDropDownCat;
    private int pageCount;
    private int[] numPages;
    private int pageCourante;
    private String searchName="";

    public lstBateauAchoisirForm() {
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
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

    public Page<qNavire> getLstBat() {
        return lstBat;
    }

    public void setLstBat(Page<qNavire> lstBat) {
        this.lstBat = lstBat;
    }

    public qLic getLicence() {
        return licence;
    }

    public void setLicence(qLic licence) {
        this.licence = licence;
    }

    public String getRefMessage() {
        return RefMessage;
    }

    public void setRefMessage(String refMessage) {
        RefMessage = refMessage;
    }

    public String getNumSelected() {
        return numSelected;
    }

    public void setNumSelected(String numSelected) {
        this.numSelected = numSelected;
    }

    public qCategRessource getSelectedDropDownCat() {
        return selectedDropDownCat;
    }

    public void setSelectedDropDownCat(qCategRessource selectedDropDownCat) {
        this.selectedDropDownCat = selectedDropDownCat;
    }
}
