package com.gardecote.web;

import com.gardecote.entities.qDoc;
import com.gardecote.entities.qPageCarnet;
import com.gardecote.entities.qTraitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 18/11/2016.
 */
public class CreateDocForm implements Serializable{
   private Integer currentPage=0;
    private String pageFin=null;

    private qDoc currentDoc=null; // en cas de ecrasement on verifie s'i s'agit de different seq alors supprimer l'ancien et si est le meme seq juste il faut l'ouvrire pour modification
    private List<qDoc> lstDoc=new ArrayList<qDoc>();
    private List<qTraitement> lstTraitement=new ArrayList<qTraitement>();

    private String typeDoc=null;
    private String titre=null;

    public CreateDocForm() {
    }

    public CreateDocForm(Integer currentPage, String pageFin, qDoc currentDoc, List<qDoc> lstDoc, List<qTraitement> lstTraitement, String typeDoc, String titre) {
        this.currentPage = currentPage;
        this.pageFin = pageFin;
        this.currentDoc = currentDoc;
        this.lstDoc = lstDoc;
        this.lstTraitement = lstTraitement;
        this.typeDoc = typeDoc;
        this.titre = titre;
    }

    public Integer getCurrentPage() {
        return currentPage;
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

    public List<qTraitement> getLstTraitement() {
        return lstTraitement;
    }

    public void setLstTraitement(List<qTraitement> lstTraitement) {
        this.lstTraitement = lstTraitement;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
