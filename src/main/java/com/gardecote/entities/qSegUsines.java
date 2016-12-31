package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 08/11/2016.
 */
@Entity
@Table(name="qSegUsines22", schema="dbo", catalog="GCM1")
@IdClass(qSegUsinesPK.class)
public class qSegUsines implements Serializable {
    @Id
    String refAgrement;
    @Id
    Date dateTraitement;
    @Id
    private enumSegPeche segPeche;
    @OneToOne
    private  qTraitement traitement;
    private boolean choix;

    private boolean ceph;
    private boolean demersal;
    private boolean pel;
    private boolean crust;
    private boolean autres;



    public qSegUsines(qTraitement traitement, enumSegPeche segPeche, boolean choix, boolean ceph, boolean demersal, boolean pel, boolean crust, boolean autres) {
        this.refAgrement = traitement.getNumImm();
        this.dateTraitement = traitement.getDepart();
        this.traitement=traitement;
        this.segPeche = segPeche;
        this.choix = choix;
        this.ceph = ceph;
        this.demersal = demersal;
        this.pel = pel;
        this.crust = crust;
        this.autres = autres;
    }

    public qSegUsines() {
    }

    public enumSegPeche getSegPeche() {
        return segPeche;
    }

    public qTraitement getTraitement() {
        return traitement;
    }

    public void setTraitement(qTraitement traitement) {
        this.traitement = traitement;
    }

    public void setSegPeche(enumSegPeche segPeche) {
        this.segPeche = segPeche;
    }

    public boolean isChoix() {
        return choix;
    }

    public void setChoix(boolean choix) {
        this.choix = choix;
    }

    public String getRefAgrement() {
        return refAgrement;
    }

    public void setRefAgrement(String refAgrement) {
        this.refAgrement = refAgrement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public boolean getCeph() {
        return ceph;
    }

    public void setCeph(boolean ceph) {
        this.ceph = ceph;
    }

    public boolean getDemersal() {
        return demersal;
    }

    public void setDemersal(boolean demersal) {
        this.demersal = demersal;
    }

    public boolean getPel() {
        return pel;
    }

    public void setPel(boolean pel) {
        this.pel = pel;
    }

    public boolean getCrust() {
        return crust;
    }

    public void setCrust(boolean crust) {
        this.crust = crust;
    }

    public boolean getAutres() {
        return autres;
    }

    public void setAutres(boolean autres) {
        this.autres = autres;
    }
}
