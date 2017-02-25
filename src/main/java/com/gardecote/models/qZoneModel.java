package com.gardecote.models;
import javax.persistence.*;
import java.io.Serializable;
public class qZoneModel implements Serializable {
    private Integer IdZone;
    private String  nom;

    public qZoneModel() {
    }

    public qZoneModel(Integer idZone, String nom) {
        IdZone = idZone;
        this.nom = nom;
    }

    public Integer getIdZone() {
        return IdZone;
    }

    public void setIdZone(Integer idZone) {
        IdZone = idZone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
