package com.gardecote.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 * Created by Dell on 25/10/2016.
 */
@Entity
public class qQuantiteExportee implements Serializable {
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

    public qQuantiteExportee(enumZonOrientation enumZonOrientationPeche, Integer qte) {
        this.enumZonOrientationPeche = enumZonOrientationPeche;
        this.qte = qte;
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
