package com.gardecote.web;

import com.gardecote.entities.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 07/04/2017.
 */
@IdClass(qJourPK.class)
public class qJour implements Serializable {
@Id
    @DateTimeFormat(pattern="yyyy-MM-dd")

    private Date dateJour     ;
    @Id

    private Integer       indexLigne   ;
    @Id
    private String      numPage   ;
    @Id
    private String numImm;

    private String secteur;
    private qNavireLegale navire;
    private List<qCapture> capturesDuMarree;
    private float totalCapturs;
    private float totalCong;
    private float nbrCaisse;
    private qPageCarnet pageCarnet;
    private qDoc document;
    private enumModePeche modePeche;
    private String typePeche;

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public Integer getIndexLigne() {
        return indexLigne;
    }

    public void setIndexLigne(Integer indexLigne) {
        this.indexLigne = indexLigne;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public qNavireLegale getNavire() {
        return navire;
    }

    public void setNavire(qNavireLegale navire) {
        this.navire = navire;
    }

    public List<qCapture> getCapturesDuMarree() {
        return capturesDuMarree;
    }

    public qDoc getDocument() {
        return document;
    }

    public void setDocument(qDoc document) {
        this.document = document;
    }

    public enumModePeche getModePeche() {
        return modePeche;
    }

    public void setModePeche(enumModePeche modePeche) {
        this.modePeche = modePeche;
    }

    public String getTypePeche() {
        return typePeche;
    }

    public void setTypePeche(String typePeche) {
        this.typePeche = typePeche;
    }

    public void setCapturesDuMarree(List<qCapture> capturesDuMarree) {
        this.capturesDuMarree = capturesDuMarree;
    }

    public float getTotalCapturs() {
        return totalCapturs;
    }

    public void setTotalCapturs(float totalCapturs) {
        this.totalCapturs = totalCapturs;
    }

    public float getTotalCong() {
        return totalCong;
    }

    public void setTotalCong(float totalCong) {
        this.totalCong = totalCong;
    }

    public float getNbrCaisse() {
        return nbrCaisse;
    }

    public void setNbrCaisse(float nbrCaisse) {
        this.nbrCaisse = nbrCaisse;
    }

    public qPageCarnet getPageCarnet() {
        return pageCarnet;
    }

    public void setPageCarnet(qPageCarnet pageCarnet) {
        this.pageCarnet = pageCarnet;
    }

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public qJour(Date dateJour, Integer indexLigne, String numPage, String numImm, String secteur, qNavireLegale navire, List<qCapture> capturesDuMarree, float totalCapturs, float totalCong, float nbrCaisse, qPageCarnet pageCarnet, qDoc document, enumModePeche modePeche, String typePeche) {
        this.dateJour = dateJour;
        this.indexLigne = indexLigne;
        this.numImm=numImm;
        this.numPage=numPage;
        this.secteur = secteur;
        this.navire = navire;
        this.capturesDuMarree = capturesDuMarree;
        this.totalCapturs = totalCapturs;
        this.totalCong = totalCong;
        this.nbrCaisse = nbrCaisse;
        this.pageCarnet = pageCarnet;
        this.document = document;
        this.modePeche = modePeche;
        this.typePeche = typePeche;
    }

    public qJour() {
    }
}
