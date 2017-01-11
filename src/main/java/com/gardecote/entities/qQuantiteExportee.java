package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 25/10/2016.
 */

@Entity
@Table(name="qQuantiteExportee30", schema="dbo", catalog="GCM1" )
@IdClass(qQuantiteExporteePK.class)
public class qQuantiteExportee implements Serializable {
    @Id
    String refAgrement;
    @Id
    Date dateTraitement;
    @Id
    private enumZonOrientation enumZonOrientationPeche;


    @OneToOne
    private qTraitement     qtraitement;

    private Integer qte;


    public qQuantiteExportee(qTraitement qtraitement,enumZonOrientation enumZonOrientationPeche, Integer qte) {
        this.refAgrement = qtraitement.getNumImm();
        this.dateTraitement = qtraitement.getDepart();
        this.enumZonOrientationPeche = enumZonOrientationPeche;
        this.qte = qte;
        this.qtraitement=qtraitement;
    }

    public qTraitement getQtraitement() {
        return qtraitement;
    }

    public void setQtraitement(qTraitement qtraitement) {
        this.qtraitement = qtraitement;
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

    public enumZonOrientation getEnumZonOrientationPeche() {
        return enumZonOrientationPeche;
    }

    public void setEnumZonOrientationPeche(enumZonOrientation enumZonOrientationPeche) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public qQuantiteExportee() {
    }
}
