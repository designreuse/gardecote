package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 25/10/2016.
 */

@Entity
@Table(name="qQuantiteExportee20", schema="dbo", catalog="GCM1" )
@IdClass(qDocPK.class)
public class qQuantiteExportee implements Serializable {
    @Id
    String numImm;
    @Id
    Date depart;
    @Id
    private enumZonOrientation enumZonOrientationPeche;

    private Integer qte;
    @OneToOne
    private qTraitement qtraitement;

    public qTraitement getQtraitement() {
        return qtraitement;
    }

    public void setQtraitement(qTraitement qtraitement) {
        this.qtraitement = qtraitement;
    }



    public qQuantiteExportee(enumZonOrientation enumZonOrientationPeche, Integer qte, qTraitement qtraitement) {
        this.numImm = qtraitement.getNumImm();
        this.depart = qtraitement.getDepart();
        this.enumZonOrientationPeche = enumZonOrientationPeche;
        this.qte = qte;
        this.qtraitement = qtraitement;
    }

    public String getNumImm() {
        return numImm;
    }

    public void setNumImm(String numImm) {
        this.numImm = numImm;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
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
