package com.gardecote.web;

import com.gardecote.entities.qDoc;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 19/03/2017.
 */

public class printedDocument {

    private qDoc currentDoc;
    private List<Object> pagesDocCaptures;
    private String numeroPage;
    private List<String> resultatsEntetesCol;
    private List<String> resultatsNamesCol;

    public String getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(String numeroPage) {
        this.numeroPage = numeroPage;
    }

    public qDoc getCurrentDoc() {
        return currentDoc;
    }

    public void setCurrentDoc(qDoc currentDoc) {
        this.currentDoc = currentDoc;
    }

    public List<Object> getPagesDocCaptures() {
        return pagesDocCaptures;
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

    public void setPagesDocCaptures(List<Object> pagesDocCaptures) {
        this.pagesDocCaptures = pagesDocCaptures;
    }

    public printedDocument(qDoc currentDoc, List<Object> pagesDoc, String numeroPage,List<String> resultatsEntetesCol,List<String> resultatsNamesCol) {

        this.currentDoc = currentDoc;
        this.pagesDocCaptures = pagesDoc;
        this.numeroPage = numeroPage;
        this.resultatsEntetesCol=resultatsEntetesCol;
                this.resultatsNamesCol=resultatsNamesCol;
    }

    public printedDocument() {
    }
}
