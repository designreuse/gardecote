package com.gardecote.web;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qUsine;
import org.springframework.core.serializer.Serializer;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Created by Dell on 27/12/2016.
 */
public class lstUsineForm implements Serializable {
    private Page<qUsine> usines;
    private int pageCount;
    private  int[] numPages;
    private int pageCourante;

    public Page<qUsine> getUsines() {
        return usines;
    }

    public void setUsines(Page<qUsine> usines) {
        this.usines = usines;
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
