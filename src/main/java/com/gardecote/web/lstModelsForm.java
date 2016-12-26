package com.gardecote.web;

import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qModelJP;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Dell on 11/12/2016.
 */

public class lstModelsForm {
    private List<qModelJP> lstModels=null;


    private String ModelPKSelected=null;

    private int pageCount;
    private int[] numPages;
    private int pageCourante;

    public List<qModelJP> getLstModels() {
        return lstModels;
    }

    public void setLstModels(List<qModelJP> lstModels) {
        this.lstModels = lstModels;
    }

    public String getModelPKSelected() {
        return ModelPKSelected;
    }

    public void setModelPKSelected(String modelPKSelected) {
        ModelPKSelected = modelPKSelected;
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


}
