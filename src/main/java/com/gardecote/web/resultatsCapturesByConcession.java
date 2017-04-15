package com.gardecote.web;
import com.gardecote.entities.qTypeConcession;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class resultatsCapturesByConcession {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchDateCapture1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchDateCapture2;
    private taskCommander task;
    private List<choixTypeConcession> types;
    private List<qTypeConcession> TypesC;
    private List<String> typesCons;
    private List<Object> resultatCaptures;
    private List<String> resultatsEntetesCol;
    private List<String> resultatsNamesCol;

    public List<String> getTypesCons() {
        return typesCons;
    }

    public void setTypesCons(List<String> typesCons) {
        this.typesCons = typesCons;
    }

    public taskCommander getTask() {
        return task;
    }

    public void setTask(taskCommander task) {
        this.task = task;
    }

    public resultatsCapturesByConcession(Date searchDateCapture1, Date searchDateCapture2, List<choixTypeConcession> types, List<Object> resultatCaptures, List<qTypeConcession> TypesC, List<String> resultatsEntetesCol, List<String> resultatsNamesCol) {
        List<String> strList=new ArrayList<String>();
        this.searchDateCapture1 = searchDateCapture1;
        this.searchDateCapture2 = searchDateCapture2;
        this.types = types;
        this.TypesC=TypesC;
        this.resultatCaptures = resultatCaptures;
        this.resultatsEntetesCol = resultatsEntetesCol;
        this.resultatsNamesCol = resultatsNamesCol;

        for(qTypeConcession con:TypesC) {
            strList.add(con.getDesignation());
            }
        this.typesCons=strList;
    }

    public List<qTypeConcession> getTypesC() {
        return TypesC;
    }

    public void setTypesC(List<qTypeConcession> typesC) {
        TypesC = typesC;
    }

    public Date getSearchDateCapture1() {
        return searchDateCapture1;
    }

    public void setSearchDateCapture1(Date searchDateCapture1) {
        this.searchDateCapture1 = searchDateCapture1;
    }


    public Date getSearchDateCapture2() {
        return searchDateCapture2;
    }

    public void setSearchDateCapture2(Date searchDateCapture2) {
        this.searchDateCapture2 = searchDateCapture2;
    }

    public List<choixTypeConcession> getTypes() {
        return types;
    }

    public void setTypes(List<choixTypeConcession> types) {
        this.types = types;
    }

    public List<Object> getResultatCaptures() {
        return resultatCaptures;
    }

    public void setResultatCaptures(List<Object> resultatCaptures) {
        this.resultatCaptures = resultatCaptures;
    }

    public List<String> getResultatsEntetesCol() {
        return resultatsEntetesCol;
    }

    public void setResultatsEntetesCol(List<String> resultatsEntetesCol) {
        this.resultatsEntetesCol = resultatsEntetesCol;
    }

    public List<String> getResultatsNamesCol() {
        return resultatsNamesCol;
    }

    public void setResultatsNamesCol(List<String> resultatsNamesCol) {
        this.resultatsNamesCol = resultatsNamesCol;
    }
}
