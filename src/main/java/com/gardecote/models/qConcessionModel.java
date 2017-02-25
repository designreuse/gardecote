package com.gardecote.models;

import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qConsignataire;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qLicenceNational;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 16/02/2017.
 */
public class qConcessionModel {

    private String     refConcession ;


    private String qconsignataire;


    private Date dateConcession  ;


    private Integer   quotaEnTonne  ;


    private Integer dureeConcession;

    private Date dateDebut    ;


    private Date     dateFin      ;

    private String     categoriesRessources ;

    public qConcessionModel() {
    }

    public String getRefConcession() {
        return refConcession;
    }

    public void setRefConcession(String refConcession) {
        this.refConcession = refConcession;
    }

    public String getQconsignataire() {
        return qconsignataire;
    }

    public void setQconsignataire(String qconsignataire) {
        this.qconsignataire = qconsignataire;
    }

    public Date getDateConcession() {
        return dateConcession;
    }

    public void setDateConcession(Date dateConcession) {
        this.dateConcession = dateConcession;
    }

    public Integer getQuotaEnTonne() {
        return quotaEnTonne;
    }

    public void setQuotaEnTonne(Integer quotaEnTonne) {
        this.quotaEnTonne = quotaEnTonne;
    }

    public Integer getDureeConcession() {
        return dureeConcession;
    }

    public void setDureeConcession(Integer dureeConcession) {
        this.dureeConcession = dureeConcession;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getCategoriesRessources() {
        return categoriesRessources;
    }

    public void setCategoriesRessources(String categoriesRessources) {
        this.categoriesRessources = categoriesRessources;
    }
}
