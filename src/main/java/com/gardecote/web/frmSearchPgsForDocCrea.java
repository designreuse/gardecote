package com.gardecote.web;

import com.gardecote.entities.enumTypeDoc;
import com.gardecote.entities.qDoc;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 03/12/2016.
 */
public class frmSearchPgsForDocCrea implements Serializable{
    private String numeroDebut;
    private String numeroFin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRetour;
    private boolean displayButton;
    private boolean displaydatefrg;
    private boolean displayFinListfrg;
    List<String> numsfin=new ArrayList<>();
    private boolean displayDocForm;
    Page<qDoc> lstDocuments;
    private CreateDocForm createDocFormm;
    private enumTypeDoc typeDoc;

    private Integer pageCount;
    private int[] numPages;
    private Integer pageCourante;

    public List<String> getNumsfin() {
        return numsfin;
    }

    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public boolean isDisplayDocForm() {
        return displayDocForm;
    }

    public CreateDocForm getCreateDocFormm() {
        return createDocFormm;
    }

    public void setCreateDocFormm(CreateDocForm createDocFormm) {
        this.createDocFormm = createDocFormm;
    }

    public void setDisplayDocForm(boolean displayDocForm) {
        this.displayDocForm = displayDocForm;
    }

    public Page<qDoc> getLstDocuments() {
        return lstDocuments;
    }

    public void setLstDocuments(Page<qDoc> lstDocuments) {
        this.lstDocuments = lstDocuments;
    }

    public void setNumsfin(List<String> numsfin) {
        this.numsfin = numsfin;
    }

    public String getNumeroDebut() {
        return numeroDebut;
    }

    public void setNumeroDebut(String numeroDebut) {
        this.numeroDebut = numeroDebut;
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

    public String getNumeroFin() {
        return numeroFin;
    }

    public void setNumeroFin(String numeroFin) {
        this.numeroFin = numeroFin;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateRetour() {
        return this.dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public boolean isDisplayButton() {
        return this.displayButton;
    }

    public void setDisplayButton(boolean displayButton) {
        this.displayButton = displayButton;
    }

    public boolean isDisplaydatefrg() {
        return displaydatefrg;
    }

    public void setDisplaydatefrg(boolean displaydatefrg) {
        this.displaydatefrg = displaydatefrg;
    }

    public boolean isDisplayFinListfrg() {
        return this.displayFinListfrg;
    }

    public void setDisplayFinListfrg(boolean displayFinListfrg) {
        this.displayFinListfrg = displayFinListfrg;
    }

}
