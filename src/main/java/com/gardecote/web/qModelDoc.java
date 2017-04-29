package com.gardecote.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 22/04/2017.
 */
public class qModelDoc implements Serializable {
    private String Npage;
    private Date   depart;
    private String secteur;
    private String  Ref;

    private String Nom;

    private String Date_dpart;
    private String Navire;

    private String chinchard;
    private String sardine;
    private String sardinelle;
    private String anchois;
    private String maquereau;
    private String sabres;
    private String thonides;
    private String mulet;
    private String merlu;
    private String calamar;
    private String diversDorades;
    private String Fish_p_chat;
    private String red_bait;
    private String seaBrum;
    private String autrePPel;
    private String autrePDem;

    public qModelDoc() {
    }

    public qModelDoc(String npage, Date depart, String secteur, String ref, String nom,  String date_dpart, String navire, String chinchard, String sardine, String sardinelle, String anchois, String maquereau, String sabres, String thonides, String mulet, String merlu, String calamar, String diversDorades, String fish_p_chat, String red_bait, String seaBrum, String autrePPel, String autrePDem) {
        this.Npage = npage;
        this.depart = depart;
        this.secteur = secteur;
        this.Ref = ref;
        this.Nom = nom;
        this.Date_dpart = date_dpart;
        this.Navire = navire;
        this.chinchard = chinchard;
        this.sardine = sardine;
        this.sardinelle = sardinelle;
        this.anchois = anchois;
        this.maquereau = maquereau;
        this.sabres = sabres;
        this.thonides = thonides;
        this.mulet = mulet;
        this.merlu = merlu;
        this.calamar = calamar;
        this.diversDorades = diversDorades;
        this.Fish_p_chat = fish_p_chat;
        this.red_bait = red_bait;
        this.seaBrum = seaBrum;
        this.autrePPel = autrePPel;
        this.autrePDem = autrePDem;
    }

    public String getNpage() {
        return Npage;
    }

    public void setNpage(String npage) {
        Npage = npage;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date départ) {
        this.depart = départ;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getChinchard() {
        return chinchard;
    }

    public void setChinchard(String chinchard) {
        this.chinchard = chinchard;
    }

    public String getSardine() {
        return sardine;
    }

    public void setSardine(String sardine) {
        this.sardine = sardine;
    }

    public String getSardinelle() {
        return sardinelle;
    }

    public void setSardinelle(String sardinelle) {
        this.sardinelle = sardinelle;
    }

    public String getAnchois() {
        return anchois;
    }

    public void setAnchois(String anchois) {
        this.anchois = anchois;
    }

    public String getMaquereau() {
        return maquereau;
    }

    public void setMaquereau(String maquereau) {
        this.maquereau = maquereau;
    }

    public String getSabres() {
        return sabres;
    }

    public void setSabres(String sabres) {
        this.sabres = sabres;
    }

    public String getThonides() {
        return thonides;
    }

    public void setThonides(String thonides) {
        this.thonides = thonides;
    }

    public String getMulet() {
        return mulet;
    }

    public void setMulet(String mulet) {
        this.mulet = mulet;
    }

    public String getMerlu() {
        return merlu;
    }

    public void setMerlu(String merlu) {
        this.merlu = merlu;
    }

    public String getCalamar() {
        return calamar;
    }

    public void setCalamar(String calamar) {
        this.calamar = calamar;
    }

    public String getDiversDorades() {
        return diversDorades;
    }

    public void setDiversDorades(String diversDorades) {
        this.diversDorades = diversDorades;
    }

    public String getFish_p_chat() {
        return Fish_p_chat;
    }

    public void setFish_p_chat(String fish_p_chat) {
        Fish_p_chat = fish_p_chat;
    }

    public String getRed_bait() {
        return red_bait;
    }

    public void setRed_bait(String red_bait) {
        this.red_bait = red_bait;
    }

    public String getSeaBrum() {
        return seaBrum;
    }

    public void setSeaBrum(String seaBrum) {
        this.seaBrum = seaBrum;
    }

    public String getAutrePPel() {
        return autrePPel;
    }

    public void setAutrePPel(String autrePPel) {
        this.autrePPel = autrePPel;
    }

    public String getAutrePDem() {
        return autrePDem;
    }

    public void setAutrePDem(String autrePDem) {
        this.autrePDem = autrePDem;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String ref) {
        Ref = ref;
    }



    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }



    public String getDate_dpart() {
        return Date_dpart;
    }

    public void setDate_dpart(String date_dpart) {
        Date_dpart = date_dpart;
    }

    public String getNavire() {
        return Navire;
    }

    public void setNavire(String navire) {
        Navire = navire;
    }
}
