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
    private String refConcession;
    private String nomConcessionnaire;
    private String support;
    private String quota;
    private String dureeCobcession;
    private String dateExpiration;
    private String licencePeche;
    private String nomNavire;
    private String imo;
    private String gt;
    private String nomCapitaine;
    private List<String> maillages ;
    private List<Boolean> choixEngins ;

      // pour le debarquements
    private String segPeche;
    private String zonePeche;
    private List<String> choisTypesConcessions;


    public String getRefConcession() {
        return refConcession;
    }

    public String getSegPeche() {
        return segPeche;
    }

    public void setSegPeche(String segPeche) {
        this.segPeche = segPeche;
    }

    public String getZonePeche() {
        return zonePeche;
    }

    public void setZonePeche(String zonePeche) {
        this.zonePeche = zonePeche;
    }

    public List<String> getChoisTypesConcessions() {
        return choisTypesConcessions;
    }

    public void setChoisTypesConcessions(List<String> choisTypesConcessions) {
        this.choisTypesConcessions = choisTypesConcessions;
    }

    public void setRefConcession(String refConcession) {
        this.refConcession = refConcession;
    }

    public String getNomConcessionnaire() {
        return nomConcessionnaire;
    }

    public List<Boolean> getChoixEngins() {
        return choixEngins;
    }

    public void setChoixEngins(List<Boolean> choixEngins) {
        this.choixEngins = choixEngins;
    }

    public void setNomConcessionnaire(String nomConcessionnaire) {
        this.nomConcessionnaire = nomConcessionnaire;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getDureeCobcession() {
        return dureeCobcession;
    }

    public void setDureeCobcession(String dureeCobcession) {
        this.dureeCobcession = dureeCobcession;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getLicencePeche() {
        return licencePeche;
    }

    public void setLicencePeche(String licencePeche) {
        this.licencePeche = licencePeche;
    }

    public String getNomNavire() {
        return nomNavire;
    }

    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getNomCapitaine() {
        return nomCapitaine;
    }

    public void setNomCapitaine(String nomCapitaine) {
        this.nomCapitaine = nomCapitaine;
    }

    public List<String> getMaillages() {
        return maillages;
    }

    public void setMaillages(List<String> maillages) {
        this.maillages = maillages;
    }

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
