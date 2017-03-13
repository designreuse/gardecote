package com.gardecote.web;

import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qConcession;
import com.gardecote.entities.qEnginAuthorisee;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dell on 09/03/2017.
 */
public class creationConcessionForm implements Serializable {
    private qCategRessource selectedCategorieRessource;
    private List<qCategRessource> categoriesRattaches;

    private qConcession currentConcession;

    public creationConcessionForm() {
    }

    public qCategRessource getSelectedCategorieRessource() {
        return selectedCategorieRessource;
    }

    public void setSelectedCategorieRessource(qCategRessource selectedCategorieRessource) {
        this.selectedCategorieRessource = selectedCategorieRessource;
    }

    public List<qCategRessource> getCategoriesRattaches() {
        return categoriesRattaches;
    }

    public qConcession getCurrentConcession() {
        return currentConcession;
    }

    public void setCurrentConcession(qConcession currentConcession) {
        this.currentConcession = currentConcession;
    }

    public void setCategoriesRattaches(List<qCategRessource> categoriesRattaches) {
        this.categoriesRattaches = categoriesRattaches;
    }




}
