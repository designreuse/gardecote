package com.gardecote.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 08/11/2016.
 */
@Entity
@Table(name="qSegUsines33", schema="dbo", catalog="GCM3")
@IdClass(qSegUsinesPK.class)
public class qSegUsines implements Serializable {
    @Id
    private String refAgrement;
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTraitement;
    @Id
    private enumSegPeche segPeche;


    private boolean choix;

    private boolean ceph;
    private boolean demersal;
    private boolean pel;
    private boolean crust;
    private boolean autres;

    public qSegUsines(String num,Date depart ,enumSegPeche segPeche, boolean choix, boolean ceph, boolean demersal, boolean pel, boolean crust, boolean autres) {
        this.refAgrement = num;
        this.dateTraitement = depart;

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

    public boolean isCeph() {
        return ceph;
    }

    public boolean isDemersal() {
        return demersal;
    }

    public boolean isPel() {
        return pel;
    }

    public boolean isCrust() {
        return crust;
    }

    public boolean isAutres() {
        return autres;
    }
}
