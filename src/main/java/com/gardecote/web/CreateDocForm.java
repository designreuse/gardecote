package com.gardecote.web;

import com.gardecote.entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 18/11/2016.
 */
public class CreateDocForm implements Serializable{
   private Integer currentPage=0;
    private Integer currentPageAnnexe=0;
    private String pageFin=null;
    private String interference=null;
    private qDoc currentDoc=null; // en cas de ecrasement on verifie s'i s'agit de different seq alors supprimer l'ancien et si est le meme seq juste il faut l'ouvrire pour modification
    private List<qDoc> lstDoc=new ArrayList<qDoc>();


    private qDoc docDoublon=null;
    private List<qDoc> lstDocJoursDup=null;
    private List<qDoc> lstDocPagesDupliquees=null;
    private qModelJP modelJP=null;
    private String SegmentPeche=null;
    private String typePeche=null;
    private String prefix=null;

// pour le traitement
    private List<qSegUsines> segmentPeches;
    private qPrefix qprefix;
    private enumTypeDoc typeDoc;
    private String titre;
    private qUsine usine;
    private List<qPageTraitement> pagesTraitements;
    private List<qQuantiteExportee> QteExportees ;
    private qQuantitesTraites QteTraitees;
    private Long qteDechu;

//


    public Integer getCurrentPageAnnexe() {
        return currentPageAnnexe;
    }

    public void setCurrentPageAnnexe(Integer currentPageAnnexe) {
        this.currentPageAnnexe = currentPageAnnexe;
    }

    public String getSegmentPeche() {
        return SegmentPeche;
    }

    public void setSegmentPeche(String segmentPeche) {
        SegmentPeche = segmentPeche;
    }

    public String getTypePeche() {
        return typePeche;
    }

    public void setTypePeche(String typePeche) {
        this.typePeche = typePeche;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public CreateDocForm() {
    }

    public qModelJP getModelJP() {
        return modelJP;
    }

    public void setModelJP(qModelJP modelJP) {
        this.modelJP = modelJP;
    }

    public CreateDocForm(Integer currentPage, String pageFin, qDoc currentDoc, List<qDoc> lstDoc,  enumTypeDoc typeDoc, String titre) {
        this.currentPage = currentPage;
        this.pageFin = pageFin;
        this.currentDoc = currentDoc;
        this.lstDoc = lstDoc;

        this.typeDoc = typeDoc;
        this.titre = titre;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public qDoc getDocDoublon() {
        return docDoublon;
    }

    public void setDocDoublon(qDoc docDoublon) {
        this.docDoublon = docDoublon;
    }

    public List<qDoc> getLstDocJoursDup() {
        return lstDocJoursDup;
    }

    public void setLstDocJoursDup(List<qDoc> lstDocJoursDup) {
        this.lstDocJoursDup = lstDocJoursDup;
    }

    public List<qDoc> getLstDocPagesDupliquees() {
        return lstDocPagesDupliquees;
    }

    public void setLstDocPagesDupliquees(List<qDoc> lstDocPagesDupliquees) {
        this.lstDocPagesDupliquees = lstDocPagesDupliquees;
    }

    public qModelJP getModelEncours() {
        return modelJP;
    }

    public void setModelEncours(qModelJP modelEncours) {
        this.modelJP = modelEncours;
    }

    public String getInterference() {
        return this.interference;
    }

    public void setInterference(String interference) {
        this.interference = interference;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageFin() {
        return pageFin;
    }

    public void setPageFin(String pageFin) {
        this.pageFin = pageFin;
    }

    public qDoc getCurrentDoc() {
        return currentDoc;
    }

    public void setCurrentDoc(qDoc currentDoc) {
        this.currentDoc = currentDoc;
    }

    public List<qDoc> getLstDoc() {
        return lstDoc;
    }

    public void setLstDoc(List<qDoc> lstDoc) {
        this.lstDoc = lstDoc;
    }



    public enumTypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(enumTypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public List<qSegUsines> getSegmentPeches() {
        return segmentPeches;
    }

    public void setSegmentPeches(List<qSegUsines> segmentPeches) {
        this.segmentPeches = segmentPeches;
    }

    public qPrefix getQprefix() {
        return qprefix;
    }

    public void setQprefix(qPrefix qprefix) {
        this.qprefix = qprefix;
    }

    public qUsine getUsine() {
        return usine;
    }

    public void setUsine(qUsine usine) {
        this.usine = usine;
    }

    public List<qPageTraitement> getPagesTraitements() {
        return pagesTraitements;
    }

    public void setPagesTraitements(List<qPageTraitement> pagesTraitements) {
        this.pagesTraitements = pagesTraitements;
    }

    public List<qQuantiteExportee> getQteExportees() {
        return QteExportees;
    }

    public void setQteExportees(List<qQuantiteExportee> qteExportees) {
        QteExportees = qteExportees;
    }


    public qQuantitesTraites getQteTraitees() {
        return QteTraitees;
    }

    public void setQteTraitees(qQuantitesTraites qteTraitees) {
        QteTraitees = qteTraitees;
    }

    public Long getQteDechu() {
        return qteDechu;
    }

    public void setQteDechu(Long qteDechu) {
        this.qteDechu = qteDechu;
    }
}
