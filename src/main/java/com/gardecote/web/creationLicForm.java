package com.gardecote.web;

import com.gardecote.business.service.qConcessionService;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 20/11/2016.
 */
public class creationLicForm implements Serializable{

    private String typeOperation;
    private String action;
    private String regime;
    private  String refCon;
    private List<qNavireLegale> lstBat=new ArrayList<qNavireLegale>();
    private qCategRessource selectedCategorieRessource;

   // @Valid
    private qLic licence;


    private String RefMessage;

    private String numSelected=null;
    private qCategRessource selectedDropDownCat;
    public String getNumSelected() {
        return numSelected;
    }

    public String getRefCon() {
        return refCon;
    }

    public void setRefCon(String refCon) {
        this.refCon = refCon;
    }

    public qCategRessource getSelectedCategorieRessource() {
        return selectedCategorieRessource;
    }

    public void setSelectedCategorieRessource(qCategRessource selectedCategorieRessource) {
        this.selectedCategorieRessource = selectedCategorieRessource;
    }

    public qCategRessource getSelectedDropDownCat() {
        return selectedDropDownCat;
    }

    public void setSelectedDropDownCat(qCategRessource selectedDropDownCat) {
        this.selectedDropDownCat = selectedDropDownCat;
    }

    public String getRefMessage() {
        return RefMessage;
    }



    public void setRefMessage(String refMessage) {
        RefMessage = refMessage;
    }

    public void setNumSelected(String numSelected) {
        this.numSelected = numSelected;
    }

    public creationLicForm() {
    }


    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public List<qNavireLegale> getLstBat() {
        return lstBat;
    }

    public void setLstBat(List<qNavireLegale> lstBat) {
        this.lstBat = lstBat;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public qLic getLicence() {
        return licence;
    }

    public void setLicence(qLic licence) {
        this.licence = licence;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public creationLicForm(String typeOperation, String action, List<qNavireLegale> lstBat) {
        this.typeOperation = typeOperation;
        this.action = action;
        this.lstBat = lstBat;
    }

}
